package main.services.classes;

import main.models.classes.UserDaoImpl;
import main.models.entities.User;
import main.models.interfaces.UserDao;
import main.services.interfaces.UserService;

/**
 * Created by Kuznetsov on 22/04/2017.
 */

public class UserServiceImpl implements UserService {

    private static UserDao userDao = new UserDaoImpl();

    public User auth(String email, String password) {

        User user = userDao.getByEmailAndPassword(email, password);

        //LOGGER.debug("user: " + user);

        if (user != null && user.isBlocked()) {
            return null;
        }

        //LOGGER.debug("user not blocked");

        return user;
    }
}