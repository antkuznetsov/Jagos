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

    void add(User student);

    void update(User student);

    void delete(int id);

    User getByEmailAndPassword(String email, String password);
}