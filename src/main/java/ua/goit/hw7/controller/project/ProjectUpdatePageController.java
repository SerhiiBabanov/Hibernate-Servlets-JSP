package ua.goit.hw7.controller.project;

import ua.goit.hw7.config.HibernateProvider;
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

@WebServlet("/projectEdit")
public class ProjectUpdatePageController extends HttpServlet {
    private ProjectService projectService;

    @Override
    public void init() throws ServletException {
        HibernateProvider dbProvider = new HibernateProvider();
        ProjectRepository projectRepository = new ProjectRepository(dbProvider);
        DeveloperRepository developerRepository = new DeveloperRepository(dbProvider);
        ProjectConverter projectConverter = new ProjectConverter();
        projectService = new ProjectService(projectRepository, developerRepository, projectConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        ProjectDto projectDto = projectService.getById(id).orElseGet(ProjectDto::new);
        req.setAttribute("project", projectDto);
        req.getRequestDispatcher("/WEB-INF/jsp/project/projectUpdate.jsp").forward(req, resp);
    }
}
