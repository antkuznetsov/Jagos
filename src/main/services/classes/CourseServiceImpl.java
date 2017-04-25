package main.services.classes;

import main.models.classes.CourseDaoImpl;
import main.models.entities.Course;
import main.models.interfaces.CourseDao;
import main.services.interfaces.CourseService;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Kuznetsov on 22/04/2017.
 */

public class CourseServiceImpl implements CourseService {

    private static final Logger LOGGER = Logger.getLogger(CourseServiceImpl.class);

    private static CourseDao courseDao = new CourseDaoImpl();

    public List<Course> getList() {
        return courseDao.getList();
    }

    public void add(Course course) {
        courseDao.add(course);
    }

    public Course getById(int id) {
        return courseDao.getById(id);
    }

    public void update(Course course) {
        courseDao.update(course);
    }

    public void delete(int id) {
        courseDao.delete(id);
    }
}
