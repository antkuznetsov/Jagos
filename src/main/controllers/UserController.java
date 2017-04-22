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

    public  static UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<User> list = userService.getList();
        req.setAttribute("list", list);

        //req.getRequestDispatcher("/users.jsp").forward(req, resp);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/users.jsp");
        dispatcher.forward(req, resp);
    }
}