package ua.goit.hw7.controller.developer;

import com.google.gson.Gson;
import ua.goit.hw7.config.HibernateProvider;
import ua.goit.hw7.model.dto.DeveloperDto;
import ua.goit.hw7.repository.DeveloperRepository;
import ua.goit.hw7.repository.ProjectRepository;
import ua.goit.hw7.repository.SkillRepository;
import ua.goit.hw7.service.DeveloperService;
import ua.goit.hw7.service.conventer.DeveloperConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet("/developers")
public class DevelopersController extends HttpServlet {

    private DeveloperService developerService;

    @Override
    public void init() {
        HibernateProvider dbProvider = new HibernateProvider();
        DeveloperRepository developerRepository = new DeveloperRepository(dbProvider);
        SkillRepository skillRepository = new SkillRepository(dbProvider);
        ProjectRepository projectRepository = new ProjectRepository(dbProvider);
        DeveloperConverter developerConverter = new DeveloperConverter();
        developerService = new DeveloperService(developerRepository,
                skillRepository, projectRepository, developerConverter);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().containsKey("id")) {
            List<DeveloperDto> developers = new ArrayList<>();
            developers.add(developerService.getById(Long.valueOf(req.getParameter("id"))).orElseGet(DeveloperDto::new));
            req.setAttribute("developers", developers);
            req.getRequestDispatcher("/WEB-INF/jsp/developer/developers.jsp").forward(req, resp);
        }
        if (req.getParameterMap().containsKey("projectId")) {
            List<DeveloperDto> developers = developerService.getByProjectId(Long.valueOf(req.getParameter("projectId")));
            req.setAttribute("developers", developers);
            req.getRequestDispatcher("/WEB-INF/jsp/developer/developers.jsp").forward(req, resp);
        }

        List<DeveloperDto> developers = developerService.getAll();
        req.setAttribute("developers", developers);
        req.getRequestDispatcher("/WEB-INF/jsp/developer/developers.jsp").forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().containsKey("id")) {
            Optional<DeveloperDto> developerDto = developerService.getById(Long.valueOf(req.getParameter("id")));
            developerDto.ifPresent((developer) -> developerService.delete(developer));
            req.removeAttribute("id");
            String redirect =
                    resp.encodeRedirectURL(req.getContextPath() + "/developers");
            resp.sendRedirect(redirect);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DeveloperDto developerDto = new DeveloperDto();
        developerDto.setName(req.getParameter("name"));
        developerDto.setUsername(req.getParameter("username"));
        developerDto.setSalary(Integer.valueOf(req.getParameter("salary")));
        developerService.create(developerDto);
        String redirect =
                resp.encodeRedirectURL(req.getContextPath() + "/developers");
        resp.sendRedirect(redirect);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestData = req.getReader().lines().collect(Collectors.joining());
        DeveloperDto developerDto = new Gson().fromJson(requestData, DeveloperDto.class);
        developerService.update(developerDto);
    }
}
