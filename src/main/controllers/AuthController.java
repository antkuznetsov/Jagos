package main.controllers;

import main.models.entities.User;
import main.services.classes.UserServiceImpl;
import main.services.interfaces.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Kuznetsov on 22/04/2017.
 */

public class AuthController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AuthController.class);

    private static UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if("logout".equalsIgnoreCase(req.getParameter("action"))) {

            req.getSession().invalidate();
            LOGGER.debug("Пользователь вышел из системы");
            resp.sendRedirect("/auth/");

        } else {
            req.getRequestDispatcher("/auth.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = userService.auth(email, password);

        if (user != null) {
            LOGGER.debug("Пользователь авторизован");
            req.getSession().setAttribute("email", email);
            req.getSession().setAttribute("group", user.getGroup());

            if(user.getGroup() == 1) {
                resp.sendRedirect(req.getContextPath() + "/dashboard/");
            } else {
                resp.sendRedirect(req.getContextPath() + "/view/");
            }
        } else {
            LOGGER.debug("Ошибка авторизации");
            resp.sendRedirect("/auth/");
        }
    }
}