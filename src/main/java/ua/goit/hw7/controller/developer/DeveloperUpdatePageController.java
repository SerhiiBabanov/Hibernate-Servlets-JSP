package ua.goit.hw7.controller.developer;

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

@WebServlet("/developerEdit")
public class DeveloperUpdatePageController extends HttpServlet {
    private DeveloperService developerService;

    @Override
    public void init() throws ServletException {
        HibernateProvider dbProvider = new HibernateProvider();
        DeveloperRepository developerRepository = new DeveloperRepository(dbProvider);
        SkillRepository skillRepository = new SkillRepository(dbProvider);
        ProjectRepository projectRepository = new ProjectRepository(dbProvider);
        DeveloperConverter developerConverter = new DeveloperConverter();
        developerService = new DeveloperService(developerRepository, skillRepository, projectRepository, developerConverter);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        DeveloperDto developerDto = developerService.getById(id).orElseGet(DeveloperDto::new);
        req.setAttribute("developer", developerDto);
        req.getRequestDispatcher("/WEB-INF/jsp/developer/developerUpdate.jsp").forward(req, resp);
    }
}
