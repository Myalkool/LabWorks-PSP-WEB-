package com.example.ris_lab3_1.servlet;

import com.example.ris_lab3_1.service.EmployeeService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "delete", urlPatterns = "/delete")
public class DeleteServlet extends HttpServlet {
    @EJB
    private EmployeeService employeeService;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        System.out.println(id);
        if (id != null && !id.isEmpty()) {
            employeeService.deleteEmployee(Long.parseLong(id));
        }
        resp.sendRedirect("/RIS_Lab3_1-1.0-SNAPSHOT/index");
    }
}