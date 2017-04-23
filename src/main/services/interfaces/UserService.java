package main.services.interfaces;

import main.models.entities.User;

import java.util.List;

/**
 * Created by Kuznetsov on 22/04/2017.
 */

public interface UserService {
    List<User> getList();

    User getById(int id);

    void add(User user);

    void update(User user);

    void delete(int id);

    User auth(String login, String password);
}