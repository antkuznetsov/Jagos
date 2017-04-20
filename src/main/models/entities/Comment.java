package main.models.entities;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Created by Kuznetsov on 13/04/2017.
 */

public class Comment {

    private static final Logger LOGGER = Logger.getLogger(Comment.class);

    static {
        DOMConfigurator.configure("log4j.xml");
    }

    private int id;
    private String content;
    private int authorId;
    private int lessonId;

    public Comment(int id, String content, int authorId, int lessonId) {
        this.id = id;
        this.content = content;
        this.authorId = authorId;
        this.lessonId = lessonId;
    }

    public Comment() {
        this.id = -1;
        this.content = null;
        this.authorId = -1;
        this.lessonId = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        return id == comment.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", authorId=" + authorId +
                ", lessonId=" + lessonId +
                '}';
    }
}