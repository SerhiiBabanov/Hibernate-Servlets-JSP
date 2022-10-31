package ua.goit.hw7.controller.company;

import com.google.gson.Gson;
import ua.goit.hw7.config.HibernateProvider;
import ua.goit.hw7.model.dto.CompanyDto;
import ua.goit.hw7.repository.CompanyRepository;
import ua.goit.hw7.service.CompanyService;
import ua.goit.hw7.service.conventer.CompanyConverter;

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

@WebServlet("/companies")
public class CompaniesController extends HttpServlet {

    private CompanyService companyService;

    @Override
    public void init() {
        HibernateProvider dbProvider = new HibernateProvider();
        CompanyRepository companyRepository = new CompanyRepository(dbProvider);
        CompanyConverter companyConverter = new CompanyConverter();
        companyService = new CompanyService(companyRepository, companyConverter);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().containsKey("id")) {
            List<CompanyDto> companies = new ArrayList<>();
            companies.add(companyService.getById(Long.valueOf(req.getParameter("id"))).orElseGet(CompanyDto::new));
            req.setAttribute("companies", companies);
            req.getRequestDispatcher("/WEB-INF/jsp/company/companies.jsp").forward(req, resp);
        }

        List<CompanyDto> companies = companyService.getAll();
        req.setAttribute("companies", companies);
        req.getRequestDispatcher("/WEB-INF/jsp/company/companies.jsp").forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().containsKey("id")) {
            Optional<CompanyDto> companyDto = companyService.getById(Long.valueOf(req.getParameter("id")));
            companyDto.ifPresent((company) -> companyService.delete(company));
            req.removeAttribute("id");
            String redirect =
                    resp.encodeRedirectURL(req.getContextPath() + "/companies");
            resp.sendRedirect(redirect);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setName(req.getParameter("name"));
        companyDto.setCountry(req.getParameter("country"));
        companyService.create(companyDto);
        String redirect =
                resp.encodeRedirectURL(req.getContextPath() + "/companies");
        resp.sendRedirect(redirect);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestData = req.getReader().lines().collect(Collectors.joining());
        CompanyDto companyDto = new Gson().fromJson(requestData, CompanyDto.class);
        companyService.update(companyDto);
    }
}
