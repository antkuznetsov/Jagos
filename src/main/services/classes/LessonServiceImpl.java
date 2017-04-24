package main.services.classes;

import main.models.classes.LessonDaoImpl;
import main.models.entities.Lesson;
import main.models.interfaces.LessonDao;
import main.services.interfaces.LessonService;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Kuznetsov on 22/04/2017.
 */

public class LessonServiceImpl implements LessonService {

    private static final Logger LOGGER = Logger.getLogger(LessonServiceImpl.class);

    private static LessonDao lessonDao = new LessonDaoImpl();

    public List<Lesson> getList() {
        return lessonDao.getList();
    }

    public void add(Lesson lesson) {
        lessonDao.add(lesson);
    }

    public Lesson getById(int id) {
        return lessonDao.getById(id);
    }

    public void update(Lesson lesson) {
        lessonDao.update(lesson);
    }

    public void delete(int id) {
        lessonDao.delete(id);
    }
}