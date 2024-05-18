package com.example.ris_lab3_1.servlet;

import java.io.*;
import java.util.List;

import com.example.ris_lab3_1.entity.Employee;
import com.example.ris_lab3_1.service.EmployeeService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "DefaultServlet", urlPatterns = "/index")
public class HelloServlet extends HttpServlet {

    @EJB
    private EmployeeService employeeService;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String departmentId = request.getParameter("departmentId");
        if ( departmentId!= null && !departmentId.isEmpty()) {
            request.setAttribute("employees",
                    employeeService.getEmployees(Long.parseLong(departmentId)));

        } else  request.setAttribute("employees", employeeService.getEmployees());
        request.setAttribute("departments", employeeService.getDepartments());
        request.setAttribute("departmentId", departmentId);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    public void destroy() {
    }
}