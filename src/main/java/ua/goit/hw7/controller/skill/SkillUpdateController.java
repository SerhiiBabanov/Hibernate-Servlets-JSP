package ua.goit.hw7.controller.skill;

import ua.goit.hw7.config.HibernateProvider;
import ua.goit.hw7.model.dto.SkillDto;
import ua.goit.hw7.repository.SkillRepository;
import ua.goit.hw7.service.SkillService;
import ua.goit.hw7.service.conventer.SkillConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/skillEdit")
public class SkillUpdateController extends HttpServlet {
    private SkillService skillService;

    @Override
    public void init() throws ServletException {
        HibernateProvider dbProvider = new HibernateProvider();
        SkillRepository skillRepository = new SkillRepository(dbProvider);
        SkillConverter skillConverter = new SkillConverter();
        skillService = new SkillService(skillRepository, skillConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        SkillDto skillDto = skillService.getById(id).orElseGet(SkillDto::new);
        req.setAttribute("skill", skillDto);
        req.getRequestDispatcher("/WEB-INF/jsp/skill/skillUpdate.jsp").forward(req, resp);
    }
}
