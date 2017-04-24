package main.controllers;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Kuznetsov on 23/04/2017.
 */

public class DashboardController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(DashboardController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("/dashboard.jsp");
        view.forward(req, resp);

        LOGGER.debug(req.getSession().getAttribute("group"));
    }
}