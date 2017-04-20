package main.models.entities;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Created by Kuznetsov on 13/04/2017.
 */

public class Lesson {

    private static final Logger LOGGER = Logger.getLogger(Lesson.class);

    static {
        DOMConfigurator.configure("log4j.xml");
    }

    private int id;
    private String title;
    private String content;
    private int courseId;

    public Lesson(int id, String title, String content, int courseId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.courseId = courseId;
    }

    public Lesson() {
        this.id = -1;
        this.title = null;
        this.content = null;
        this.courseId = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lesson lesson = (Lesson) o;

        return id == lesson.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", courseId=" + courseId +
                '}';
    }
}