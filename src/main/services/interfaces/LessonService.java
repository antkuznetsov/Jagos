package main.services.interfaces;

import main.models.entities.Lesson;

import java.util.List;

/**
 * Created by Kuznetsov on 22/04/2017.
 */

public interface LessonService {
    List<Lesson> getList();

    Lesson getById(int id);

    void add(Lesson lesson);

    void update(Lesson lesson);

    void delete(int id);
}