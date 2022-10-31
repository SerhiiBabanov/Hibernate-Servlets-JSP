package ua.goit.hw7.controller.project;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.goit.hw7.config.HibernateProvider;
import ua.goit.hw7.config.LocalDateDeserializer;
import ua.goit.hw7.model.dto.ProjectDto;
import ua.goit.hw7.repository.DeveloperRepository;
import ua.goit.hw7.repository.ProjectRepository;
import ua.goit.hw7.service.ProjectService;
import ua.goit.hw7.service.conventer.ProjectConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet("/projects")
public class ProjectsController extends HttpServlet {

    private ProjectService projectService;

    @Override
    public void init() {
        HibernateProvider dbProvider = new HibernateProvider();
        ProjectRepository projectRepository = new ProjectRepository(dbProvider);
        DeveloperRepository developerRepository = new DeveloperRepository(dbProvider);
        ProjectConverter projectConverter = new ProjectConverter();
        projectService = new ProjectService(projectRepository, developerRepository, projectConverter);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().containsKey("id")) {
            List<ProjectDto> projects = new ArrayList<>();
            projects.add(projectService.getById(Long.valueOf(req.getParameter("id"))).orElseGet(ProjectDto::new));
            req.setAttribute("projects", projects);
            req.getRequestDispatcher("/WEB-INF/jsp/project/projects.jsp").forward(req, resp);
        }

        if (req.getParameterMap().containsKey("developerId")) {
            List<ProjectDto> projects = projectService.getProjectsByDeveloperId(Long.valueOf(req.getParameter("developerId")));
            req.setAttribute("projects", projects);
            req.getRequestDispatcher("/WEB-INF/jsp/project/projects.jsp").forward(req, resp);
        }

        List<ProjectDto> projects = projectService.getAll();
        req.setAttribute("projects", projects);
        req.getRequestDispatcher("/WEB-INF/jsp/project/projects.jsp").forward(req, resp);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().containsKey("id")) {
            Optional<ProjectDto> projectDto = projectService.getById(Long.valueOf(req.getParameter("id")));
            projectDto.ifPresent((project) -> projectService.delete(project));
            req.removeAttribute("id");
            String redirect =
                    resp.encodeRedirectURL(req.getContextPath() + "/projects");
            resp.sendRedirect(redirect);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setName(req.getParameter("name"));
        projectDto.setGit_url(req.getParameter("git_url"));
        projectDto.setCost(Integer.valueOf(req.getParameter("cost")));
        projectDto.setDate(LocalDate.parse(req.getParameter("date")));
        projectService.create(projectDto);
        String redirect =
                resp.encodeRedirectURL(req.getContextPath() + "/projects");
        resp.sendRedirect(redirect);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestData = req.getReader().lines().collect(Collectors.joining());
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        ProjectDto projectDto = gson.fromJson(requestData, ProjectDto.class);
        projectService.update(projectDto);
    }
}
