package main.controllers;

import main.models.entities.Course;
import main.models.entities.Lesson;
import main.services.classes.CourseServiceImpl;
import main.services.classes.LessonServiceImpl;
import main.services.interfaces.CourseService;
import main.services.interfaces.LessonService;
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
import java.util.List;

/**
 * Created by Kuznetsov on 22/04/2017.
 */

public class LessonController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LessonController.class);

    //@Autowired
    //private CourseService courseService;
    @Autowired
    private LessonService lessonService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.
                processInjectionBasedOnServletContext(this,
                        config.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String lessonId = req.getParameter("lessonId");

        if(lessonId == null || lessonId.length() == 0) {

            Lesson lesson = new Lesson(
                    1,
                    req.getParameter("title"),
                    req.getParameter("content"),
                    Integer.parseInt(req.getParameter("courseId"))
            );
            lessonService.add(lesson);

        } else {
            Lesson lesson = new Lesson(
                    Integer.parseInt(lessonId),
                    req.getParameter("title"),
                    req.getParameter("content"),
                    Integer.parseInt(req.getParameter("courseId"))
            );
            lessonService.update(lesson);
        }

        resp.sendRedirect("/courses/?action=edit&id=" + Integer.parseInt(req.getParameter("courseId")));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String forward = null;
        String action = req.getParameter("action");
        String courseId = req.getParameter("course");

        if ("new".equalsIgnoreCase(action)) {

            req.setAttribute("title", "Добавить урок");
            req.setAttribute("courseId", courseId);

            forward = "/lessons/add-edit.jsp";

            RequestDispatcher view = req.getRequestDispatcher(forward);
            view.forward(req, resp);

        } else if ("delete".equalsIgnoreCase(action)) {

            lessonService.delete(Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect("/courses/?action=edit&id=" + Integer.parseInt(courseId));

        } else if ("edit".equalsIgnoreCase(action)) {
            req.setAttribute("title", "Изменить урок");

            Lesson lesson = lessonService.getById(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("lesson", lesson);

            forward = "/lessons/add-edit.jsp";

            RequestDispatcher view = req.getRequestDispatcher(forward);
            view.forward(req, resp);
        }
    }
}