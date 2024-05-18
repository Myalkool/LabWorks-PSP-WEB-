package com.example.ris_lab3_1.servlet;

import com.example.ris_lab3_1.entity.Department;
import com.example.ris_lab3_1.entity.Employee;
import com.example.ris_lab3_1.service.EmployeeService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "update", urlPatterns = "/update")
public class UpdateServlet extends HttpServlet {

    @EJB
    private EmployeeService employeeService;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            Employee employee = employeeService.getEmployee(Long.parseLong(id));
            request.setAttribute("employee", employee);
            request.setAttribute("departmentId", employee.getDepartment().getId());
        }

        request.setAttribute("departments", employeeService.getDepartments());
        getServletContext().getRequestDispatcher("/update.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Employee employee = Employee.builder()
                .firstName(req.getParameter("first"))
                .lastName(req.getParameter("last"))
                .salary(Double.parseDouble(req.getParameter("salary")))
                .department(
                        Department
                                .builder()
                                .id(Long.parseLong(req.getParameter("department")))
                                .build()
                )
                .build();

        String id = req.getParameter("id");
        if (id != null && !id.isEmpty()) {
            employee.setId(Long.parseLong(id));
            employeeService.updateEmployee(employee);
        } else {
            employeeService.addEmployee(employee);
        }

        resp.sendRedirect("/RIS_Lab3_1-1.0-SNAPSHOT/index");
    }

}