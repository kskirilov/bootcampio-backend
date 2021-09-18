package io.bootcamp.BootcampBackend.feedback;

import java.util.Objects;

public class Feedback {
    private int id;
    private int courseId;
    private int userId;
    private double courseRating;
    private String comment;

    public Feedback(int id, int courseId, int userId, double courseRating, String comment) {
        this.id = id;
        this.courseId = courseId;
        this.userId = userId;
        this.courseRating = courseRating;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getCourseRating() {
        return courseRating;
    }

    public void setCourseRating(double courseRating) {
        this.courseRating = courseRating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", userId=" + userId +
                ", courseRating=" + courseRating +
                ", comment='" + comment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return id == feedback.id && courseId == feedback.courseId && userId == feedback.userId && Double.compare(feedback.courseRating, courseRating) == 0 && Objects.equals(comment, feedback.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, userId, courseRating, comment);
    }
}
