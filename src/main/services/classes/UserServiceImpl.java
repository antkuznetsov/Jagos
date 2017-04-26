package main.services.classes;

import main.models.entities.User;
import main.models.interfaces.UserDao;
import main.services.interfaces.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Kuznetsov on 22/04/2017.
 */

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    public User auth(String email, String password) {

        User user = userDao.getByEmailAndPassword(email, password);
        System.out.println();

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

    public void add(User user) {
        userDao.add(user);
    }

    public User getById(int id) {
        return userDao.getById(id);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void delete(int id) {
        userDao.delete(id);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}