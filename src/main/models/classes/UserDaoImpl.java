package main.models.classes;

import main.models.MyConnection;
import main.models.entities.User;
import main.models.interfaces.UserDao;
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

public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    public List<User> getList() {

        List<User> users = new ArrayList<User>();

        Connection connection = MyConnection.connect();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users ORDER BY id"
            );

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                User user = new User(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getInt(6),
                        result.getBoolean(7)
                );
                users.add(user);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getStackTrace());
        }
        return users;
    }

    public User getById(int id) {

        User user = null;

        Connection connection = MyConnection.connect();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users WHERE id = ?"
            );

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                user = new User(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getInt(6),
                        result.getBoolean(7)
                );
            }

        } catch (SQLException e) {
            LOGGER.error(e.getStackTrace());
        }

        return user;
    }

    public void add(User user) {

        Connection connection = MyConnection.connect();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users (name, last_name, email, password, group_id, is_blocked) values (?,?,?,?,?,?)"
            );

            statement.setString(1, user.getName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getGroup());
            statement.setBoolean(6, user.isBlocked());

            if (statement.executeUpdate() > 0) {
                LOGGER.debug("Пользователь добавлен");
            } else {
                LOGGER.debug("Ошибка при добавлении пользователя");
            }

        } catch (SQLException e) {
            LOGGER.error(e.getStackTrace());
        }
    }

    public void delete(int id) {

        Connection connection = MyConnection.connect();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE from users WHERE id = ?"
            );

            statement.setInt(1, id);

            if (statement.executeUpdate() > 0) {
                LOGGER.debug("Пользователь удален");
            } else {
                LOGGER.debug("Ошибка при удалении пользователя");
            }

        } catch (SQLException e) {
            LOGGER.error(e.getStackTrace());
        }
    }

    public void update(User user) {

        Connection connection = MyConnection.connect();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE users set name = ?, last_name = ?, email = ?, group_id = ?, is_blocked = ? WHERE id = ?"
            );

            statement.setString(1, user.getName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getGroup());
            statement.setBoolean(5, user.isBlocked());
            statement.setInt(6, user.getId());

            if (statement.executeUpdate() > 0) {
                LOGGER.debug("Пользователь обновлен");
            } else {
                LOGGER.debug("Ошибка при обновлении пользователя");
            }

        } catch (SQLException e) {
            LOGGER.error(e.getStackTrace());
        }
    }

    public User getByEmailAndPassword(String email, String password) {

        User user = null;

        Connection connection = MyConnection.connect();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users WHERE email = ? AND password = ?"
            );

            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                user = new User(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getInt(6),
                        result.getBoolean(7)
                );
            }

        } catch (SQLException e) {
            LOGGER.error(e.getStackTrace());
        }

        return user;
    }
}