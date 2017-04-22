package main.services.interfaces;

import main.models.entities.User;

import java.util.List;

/**
 * Created by Kuznetsov on 22/04/2017.
 */

public interface UserService {
    User auth(String login, String password);
    List<User> getList();
}