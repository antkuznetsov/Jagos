package main.models.interfaces;

import main.models.entities.User;

/**
 * Created by Kuznetsov on 22/04/2017.
 */

public interface UserDao {
    User getByEmailAndPassword (String email, String password);
}