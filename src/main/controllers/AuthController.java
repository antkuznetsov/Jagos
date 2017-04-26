package main.controllers;

import main.models.entities.User;
import main.services.interfaces.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
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

    @Autowired
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.
                processInjectionBasedOnServletContext(this,
                        config.getServletContext());
    }

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
        String password = DigestUtils.md5Hex(req.getParameter("password"));

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
            //HttpSession session = req.getSession(false);
            //session.setAttribute("er", "Ошибка авторизации!");
            //resp.sendRedirect("/auth/");

            req.setAttribute("er", "Ошибка авторизации");
            RequestDispatcher view = req.getRequestDispatcher("/auth.jsp");
            view.forward(req, resp);
        }
    }
}