/*
package by.iba.controller;

import by.iba.dao.UserDao;
import by.iba.model.User;
import by.iba.util.HashPassword;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String name = request.getParameter("newLoginName");
        String password = request.getParameter("newPassword");

        UserDao daoUser = new UserDao();
        User user = new User(name, HashPassword.getHash(password));
        if (daoUser.insertUser(user)) {

            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        } else {

            request.setAttribute("errorRegister", "Выберите другое имя, такой пользователь существет");
            request.getRequestDispatcher("/WEB-INF/views/register.jsp")
                    .forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request
                .getRequestDispatcher("/WEB-INF/views/register.jsp")
                .forward(request, response);

    }
}

*/
