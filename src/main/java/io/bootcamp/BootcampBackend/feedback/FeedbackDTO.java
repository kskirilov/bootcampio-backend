package io.bootcamp.BootcampBackend.feedback;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class FeedbackDTO {
    private int id;
    private int courseId;
    private int userId;
    private double rating;
    private String comment;
    private LocalDateTime createdAt;

    public FeedbackDTO() {
    }

    public FeedbackDTO(int id, int courseId, int userId, double rating, String comment, LocalDateTime createdAt) {
        this.id = id;
        this.courseId = courseId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "FeedbackDTO{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", userId=" + userId +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedbackDTO that = (FeedbackDTO) o;
        return id == that.id && courseId == that.courseId && userId == that.userId && Double.compare(that.rating, rating) == 0 && Objects.equals(comment, that.comment) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, userId, rating, comment, createdAt);
    }
}
