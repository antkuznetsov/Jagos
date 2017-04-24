package main.models.interfaces;

import main.models.entities.Lesson;

import java.util.List;

/**
 * Created by Kuznetsov on 22/04/2017.
 */

public interface LessonDao {
    List<Lesson> getListByCourseId(int courseId);

    List<Lesson> getList();

    Lesson getById(int id);

    void add(Lesson lesson);

    void update(Lesson lesson);

    void delete(int id);
}