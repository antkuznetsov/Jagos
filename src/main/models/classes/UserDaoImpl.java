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
                    "SELECT * FROM users"
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
        return null;
    }

    public int add(User student) {
        return 0;
    }

    public int delete(int id) {
        return 0;
    }

    public int update(User student) {
        return 0;
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