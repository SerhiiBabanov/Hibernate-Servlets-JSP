package ua.goit.hw7.controller.customer;

import com.google.gson.Gson;
import ua.goit.hw7.config.HibernateProvider;
import ua.goit.hw7.model.dto.CustomerDto;
import ua.goit.hw7.repository.CustomerRepository;
import ua.goit.hw7.service.CustomerService;
import ua.goit.hw7.service.conventer.CustomersConverter;

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

@WebServlet("/customers")
public class CustomersController extends HttpServlet {

    private CustomerService customerService;

    @Override
    public void init() {
        HibernateProvider dbProvider = new HibernateProvider();
        CustomerRepository customerRepository = new CustomerRepository(dbProvider);
        CustomersConverter customersConverter = new CustomersConverter();
        customerService = new CustomerService(customerRepository, customersConverter);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().containsKey("id")) {
            List<CustomerDto> customers = new ArrayList<>();
            customers.add(customerService.getById(Long.valueOf(req.getParameter("id"))).orElseGet(CustomerDto::new));
            req.setAttribute("customers", customers);
            req.getRequestDispatcher("/WEB-INF/jsp/customer/customers.jsp").forward(req, resp);
        }

        List<CustomerDto> customers = customerService.getAll();
        req.setAttribute("customers", customers);
        req.getRequestDispatcher("/WEB-INF/jsp/customer/customers.jsp").forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().containsKey("id")) {
            Optional<CustomerDto> customerDto = customerService.getById(Long.valueOf(req.getParameter("id")));
            customerDto.ifPresent((customer) -> customerService.delete(customer));
            req.removeAttribute("id");
            String redirect =
                    resp.encodeRedirectURL(req.getContextPath() + "/customers");
            resp.sendRedirect(redirect);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName(req.getParameter("name"));
        customerDto.setEmail(req.getParameter("email"));
        customerService.create(customerDto);
        String redirect =
                resp.encodeRedirectURL(req.getContextPath() + "/customers");
        resp.sendRedirect(redirect);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestData = req.getReader().lines().collect(Collectors.joining());
        CustomerDto customerDto = new Gson().fromJson(requestData, CustomerDto.class);
        customerService.update(customerDto);
    }
}
