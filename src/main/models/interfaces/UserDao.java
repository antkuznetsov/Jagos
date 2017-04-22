package main.models.interfaces;

import main.models.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kuznetsov on 22/04/2017.
 */

public interface UserDao {
    List<User> getList();

    User getById(int id);

    int add(User student);

    int delete(int id);

    int update(User student);

    User getByEmailAndPassword(String email, String password);
}