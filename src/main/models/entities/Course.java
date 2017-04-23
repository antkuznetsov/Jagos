package main.models.entities;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Created by Kuznetsov on 13/04/2017.
 */

public class Course {

    private static final Logger LOGGER = Logger.getLogger(Course.class);

    private int id;
    private String title;
    private String description;
    private int authorId;

    public Course(int id, String title, String description, int authorId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.authorId = authorId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (id != course.id) return false;
        if (authorId != course.authorId) return false;
        if (title != null ? !title.equals(course.title) : course.title != null) return false;
        return description != null ? description.equals(course.description) : course.description == null;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", authorId=" + authorId +
                '}';
    }
}