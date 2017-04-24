package main.models.classes;

import main.models.MyConnection;
import main.models.entities.Lesson;
import main.models.interfaces.LessonDao;
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

public class LessonDaoImpl implements LessonDao {

    private static final Logger LOGGER = Logger.getLogger(LessonDaoImpl.class);

    public List<Lesson> getListByCourseId(int courseId) {
        List<Lesson> lessons = new ArrayList<Lesson>();

        Connection connection = MyConnection.connect();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM lessons WHERE course_id = ? ORDER BY id"
            );

            statement.setInt(1, courseId);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Lesson lesson = new Lesson(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getInt(4)
                );
                lessons.add(lesson);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getStackTrace());
        }
        return lessons;
    }

    public List<Lesson> getList() {

        List<Lesson> lessons = new ArrayList<Lesson>();

        Connection connection = MyConnection.connect();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM lessons ORDER BY id"
            );

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Lesson lesson = new Lesson(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getInt(4)
                );
                lessons.add(lesson);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getStackTrace());
        }
        return lessons;
    }

    public Lesson getById(int id) {

        Lesson lesson = null;

        Connection connection = MyConnection.connect();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM lessons WHERE id = ?"
            );

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                lesson = new Lesson(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getInt(4)
                );
            }

        } catch (SQLException e) {
            LOGGER.error(e.getStackTrace());
        }

        return lesson;
    }

    public void add(Lesson lesson) {

        Connection connection = MyConnection.connect();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO lessons (title, content, course_id) values (?, ?, ?)"
            );

            statement.setString(1, lesson.getTitle());
            statement.setString(2, lesson.getContent());
            statement.setInt(3, lesson.getCourseId());

            if (statement.executeUpdate() > 0) {
                LOGGER.debug("Урок добавлен");
            } else {
                LOGGER.debug("Ошибка при добавлении урока");
            }

        } catch (SQLException e) {
            LOGGER.error(e.getStackTrace());
        }
    }

    public void delete(int id) {

        Connection connection = MyConnection.connect();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE from lessons WHERE id = ?"
            );

            statement.setInt(1, id);

            if (statement.executeUpdate() > 0) {
                LOGGER.debug("Урок удален");
            } else {
                LOGGER.debug("Ошибка при удалении урока");
            }

        } catch (SQLException e) {
            LOGGER.error(e.getStackTrace());
        }
    }

    public void update(Lesson lesson) {

        Connection connection = MyConnection.connect();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE lessons SET title = ?, content = ?, course_id = ? WHERE id = ?"
            );

            statement.setString(1, lesson.getTitle());
            statement.setString(2, lesson.getContent());
            statement.setInt(3, lesson.getCourseId());
            statement.setInt(4, lesson.getId());

            if (statement.executeUpdate() > 0) {
                LOGGER.debug("Урок обновлен");
            } else {
                LOGGER.debug("Ошибка при обновлении урока");
            }

        } catch (SQLException e) {
            LOGGER.error(e.getStackTrace());
        }
    }
}