package main.controllers;

import main.services.classes.UserServiceImpl;
import main.services.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Kuznetsov on 22/04/2017.
 */

public class AuthController extends HttpServlet {

    private static UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/auth.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (userService.auth(email, password) != null) {
            req.getSession().setAttribute("email", email);
            resp.sendRedirect(req.getContextPath() + "/dashboard/");
            //Авторизован
        } else {
            resp.sendRedirect("/auth/");
            //Обратно на форму
        }
    }
}