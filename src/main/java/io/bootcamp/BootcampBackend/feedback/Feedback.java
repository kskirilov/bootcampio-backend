package io.bootcamp.BootcampBackend.feedback;

import io.bootcamp.BootcampBackend.course.Course;
import io.bootcamp.BootcampBackend.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Table(name = "feedback")
@Entity(name = "Feedback")
public class Feedback {

    @Id
    @SequenceGenerator(
            name = "session_sequence",
            sequenceName = "session_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "session_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private int id;

    @ManyToOne
    @JoinColumn(
            name = "course_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "fk_course_id"
            )
    )
    private Course courseId;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "fk_user_id"
    )
    )
    private User userId;

    @Column(name = "course_rating")
    private double courseRating;

    @Column(name = "comment")
    private String comment;

    @Column(
            name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist(){
        createdAt = LocalDateTime.now();
    }

    public Feedback(){};

    public Feedback(Course courseId, User userId, double courseRating, String comment) {
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

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", userId=" + userId +
                ", courseRating=" + courseRating +
                ", comment='" + comment + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return id == feedback.id && courseId == feedback.courseId && Double.compare(feedback.courseRating, courseRating) == 0 && Objects.equals(userId, feedback.userId) && Objects.equals(comment, feedback.comment) && Objects.equals(createdAt, feedback.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, userId, courseRating, comment, createdAt);
    }
}
