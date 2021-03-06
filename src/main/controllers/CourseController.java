package main.controllers;

import main.models.JagosException;
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

public class CourseController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;
    @Autowired
    private LessonService lessonService;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String courseId = req.getParameter("courseId");
        int authorId = 0;

        if ((req.getParameter("authorId")) == null) {
            authorId = 1; // Пользователь с ID 1
        } else {
            authorId = Integer.parseInt(req.getParameter("authorId"));
        }

        if(courseId == null || courseId.length() == 0) {

            Course course = new Course(
                    1,
                    req.getParameter("title"),
                    req.getParameter("description"),
                    authorId
            );
            courseService.add(course);

        } else {
            Course course = new Course(
                    Integer.parseInt(courseId),
                    req.getParameter("title"),
                    req.getParameter("description"),
                    authorId
            );
            courseService.update(course);
        }

        resp.sendRedirect("/courses/");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String forward = "";
        String action = req.getParameter("action");
        String courseId = req.getParameter("id");

        if ("new".equalsIgnoreCase(action)) {

            req.setAttribute("title", "Добавить курс");

            List<User> authors = userService.getList();
            req.setAttribute("authors", authors);

            forward = "/courses/add-edit.jsp";

        } else if ("delete".equalsIgnoreCase(action)) {
            forward = "/courses/list.jsp";
            /*
            try {

            }
            catch (JagosException ex){
                //redirect or popup
            }
            */
            courseService.delete(Integer.parseInt(req.getParameter("id")));
            List<Course> list = courseService.getList();
            req.setAttribute("list", list);
        } else if ("edit".equalsIgnoreCase(action)) {
            req.setAttribute("title", "Изменить курс");

            Course course = courseService.getById(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("course", course);

            List<Lesson> list = lessonService.getListByCourseId(Integer.parseInt(courseId));
            req.setAttribute("list", list);

            List<User> authors = userService.getList();
            req.setAttribute("authors", authors);

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