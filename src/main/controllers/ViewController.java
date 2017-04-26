package main.controllers;

import main.models.entities.Course;
import main.models.entities.Lesson;
import main.models.entities.User;
import main.services.classes.CourseServiceImpl;
import main.services.classes.LessonServiceImpl;
import main.services.classes.UserServiceImpl;
import main.services.interfaces.CourseService;
import main.services.interfaces.LessonService;
import main.services.interfaces.UserService;
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

public class ViewController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ViewController.class);

    @Autowired
    private CourseService courseService;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int courseId = 0;

        if (req.getParameter("course") != null) {
            courseId = Integer.parseInt(req.getParameter("course"));
        }

        List<Course> list = courseService.getList();
        req.setAttribute("list", list);

        if( courseId != 0 && req.getParameter("lesson") != null ){
            LOGGER.debug(req.getParameter("course") + " и " + req.getParameter("lesson"));

            Lesson lesson = lessonService.getById(Integer.parseInt(req.getParameter("lesson")));
            req.setAttribute("lesson", lesson);

            RequestDispatcher view = req.getRequestDispatcher("/view/lesson-detail.jsp");
            view.forward(req, resp);

        } else if(courseId != 0) {
            LOGGER.debug(req.getParameter("course"));

            Course course = courseService.getById(courseId);
            req.setAttribute("course", course);

            List<Lesson> lessonsList = lessonService.getListByCourseId(courseId);
            req.setAttribute("lessonsList", lessonsList);

            RequestDispatcher view = req.getRequestDispatcher("/view/lesson-list.jsp");
            view.forward(req, resp);

        } else {
            LOGGER.debug("Главная");

            RequestDispatcher view = req.getRequestDispatcher("/view/main.jsp");
            view.forward(req, resp);
        }
    }
}