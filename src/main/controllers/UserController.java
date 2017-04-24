package main.controllers;

import main.models.entities.User;
import main.services.classes.UserServiceImpl;
import main.services.interfaces.UserService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Kuznetsov on 22/04/2017.
 */

public class UserController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    public static UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userId = req.getParameter("userId");

        int group = 0;
        boolean isBlocked = false;

        if ((req.getParameter("group")) == null) {
            group = 2; // Обычный пользователь
        } else {
            group = Integer.parseInt(req.getParameter("group"));
        }

        if((req.getParameter("isBlocked")) == null) {
            isBlocked = false;
        } else {
            isBlocked = true;
        }

        LOGGER.debug(isBlocked);

        if(userId == null || userId.length() == 0) {

            User user = new User(
                    1,
                    req.getParameter("name"),
                    req.getParameter("lastName"),
                    req.getParameter("email"),
                    req.getParameter("password"),
                    group,
                    isBlocked
            );
            userService.add(user);

        } else {
            User user = new User(
                    Integer.parseInt(userId),
                    req.getParameter("name"),
                    req.getParameter("lastName"),
                    req.getParameter("email"),
                    req.getParameter("password"),
                    group,
                    isBlocked
            );
            userService.update(user);
        }

        resp.sendRedirect("/users/");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String forward = "";
        String action = req.getParameter("action");

        if ("new".equalsIgnoreCase(action)) {
            req.setAttribute("title", "Добавить пользователя");
            forward = "/users/add-edit.jsp";
        } else if ("delete".equalsIgnoreCase(action)) {
            forward = "/users/list.jsp";
            userService.delete(Integer.parseInt(req.getParameter("id")));
            List<User> list = userService.getList();
            req.setAttribute("list", list);
        } else if ("edit".equalsIgnoreCase(action)) {
            req.setAttribute("title", "Изменить пользователя");
            User user = userService.getById(Integer.parseInt(req.getParameter("id")));
            LOGGER.debug(user);
            req.setAttribute("user", user);
            forward = "/users/add-edit.jsp";

        } else if ("update".equalsIgnoreCase(action)) {

        } else {
            forward = "/users/list.jsp";
            List<User> list = userService.getList();
            req.setAttribute("list", list);
        }

        RequestDispatcher view = req.getRequestDispatcher(forward);
        view.forward(req, resp);
    }
}