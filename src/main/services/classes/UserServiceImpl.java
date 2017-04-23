package main.services.classes;

import main.models.classes.UserDaoImpl;
import main.models.entities.User;
import main.models.interfaces.UserDao;
import main.services.interfaces.UserService;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Kuznetsov on 22/04/2017.
 */

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    private static UserDao userDao = new UserDaoImpl();

    public User auth(String email, String password) {

        User user = userDao.getByEmailAndPassword(email, password);

        LOGGER.debug(user);

        if (user != null && user.isBlocked()) {
            LOGGER.debug("User is blocked");
            return null;
        }

        return user;
    }

    public List<User> getList() {
        return userDao.getList();
    }

    public int add(User user) {
        return userDao.add(user);
    }

    public User getById(int id) {
        return userDao.getById(id);
    }

    public int update(User user) {
        return userDao.update(user);
    }
}