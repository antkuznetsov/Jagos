package main.services.interfaces;

import main.models.entities.User;

/**
 * Created by Kuznetsov on 22/04/2017.
 */

public interface UserService {
    User auth(String login, String password);
}