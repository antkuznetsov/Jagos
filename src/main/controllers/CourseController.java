package main.controllers;

import main.models.entities.Course;
import main.services.classes.CourseServiceImpl;
import main.services.interfaces.CourseService;
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

public class CourseController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CourseController.class);

    public static CourseService courseService = new CourseServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String courseId = req.getParameter("courseId");

        if(courseId == null || courseId.length() == 0) {

            Course course = new Course(
                    1,
                    req.getParameter("title"),
                    req.getParameter("description"),
                    Integer.parseInt(req.getParameter("authorId"))
            );
            courseService.add(course);

        } else {
            Course course = new Course(
                    Integer.parseInt(courseId),
                    req.getParameter("title"),
                    req.getParameter("description"),
                    Integer.parseInt(req.getParameter("authorId"))
            );
            courseService.update(course);
        }

        resp.sendRedirect("/courses/");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String forward = "";
        String action = req.getParameter("action");

        if ("new".equalsIgnoreCase(action)) {
            req.setAttribute("title", "Добавить курс");
            forward = "/courses/add-edit.jsp";
        } else if ("delete".equalsIgnoreCase(action)) {
            forward = "/courses/list.jsp";
            courseService.delete(Integer.parseInt(req.getParameter("id")));
            List<Course> list = courseService.getList();
            req.setAttribute("list", list);
        } else if ("edit".equalsIgnoreCase(action)) {
            req.setAttribute("title", "Изменить курс");
            Course course = courseService.getById(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("course", course);
            forward = "/courses/add-edit.jsp";

        } else if ("update".equalsIgnoreCase(action)) {

        } else {
            forward = "/courses/list.jsp";
            List<Course> list = courseService.getList();
            req.setAttribute("list", list);
        }

        RequestDispatcher view = req.getRequestDispatcher(forward);
        view.forward(req, resp);
    }
}