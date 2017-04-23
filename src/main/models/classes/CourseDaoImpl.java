package main.models.classes;

import main.models.MyConnection;
import main.models.entities.Course;
import main.models.interfaces.CourseDao;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kuznetsov on 22/04/2017.
 */

public class CourseDaoImpl implements CourseDao {

    private static final Logger LOGGER = Logger.getLogger(CourseDaoImpl.class);

    public List<Course> getList() {

        List<Course> courses = new ArrayList<Course>();

        Connection connection = MyConnection.connect();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM courses ORDER BY id"
            );

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Course course = new Course(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getInt(4)
                );
                courses.add(course);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getStackTrace());
        }
        return courses;
    }

    public Course getById(int id) {

        Course course = null;

        Connection connection = MyConnection.connect();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM courses WHERE id = ?"
            );

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                course = new Course(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getInt(4)
                );
            }

        } catch (SQLException e) {
            LOGGER.error(e.getStackTrace());
        }

        return course;
    }

    public void add(Course course) {

        Connection connection = MyConnection.connect();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO courses (title, description, author_id) values (?, ?, ?)"
            );

            statement.setString(1, course.getTitle());
            statement.setString(2, course.getDescription());
            statement.setInt(3, course.getAuthorId());

            if (statement.executeUpdate() > 0) {
                LOGGER.debug("Курс добавлен");
            } else {
                LOGGER.debug("Ошибка при добавлении курса");
            }

        } catch (SQLException e) {
            LOGGER.error(e.getStackTrace());
        }
    }

    public void delete(int id) {

        Connection connection = MyConnection.connect();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE from courses WHERE id = ?"
            );

            statement.setInt(1, id);

            if (statement.executeUpdate() > 0) {
                LOGGER.debug("Курс удален");
            } else {
                LOGGER.debug("Ошибка при удалении курса");
            }

        } catch (SQLException e) {
            LOGGER.error(e.getStackTrace());
        }
    }

    public void update(Course course) {

        Connection connection = MyConnection.connect();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE courses SET title = ?, description = ?, author_id = ? WHERE id = ?"
            );

            statement.setString(1, course.getTitle());
            statement.setString(2, course.getDescription());
            statement.setInt(3, course.getAuthorId());
            statement.setInt(4, course.getId());

            if (statement.executeUpdate() > 0) {
                LOGGER.debug("Курс обновлен");
            } else {
                LOGGER.debug("Ошибка при обновлении курса");
            }

        } catch (SQLException e) {
            LOGGER.error(e.getStackTrace());
        }
    }
}