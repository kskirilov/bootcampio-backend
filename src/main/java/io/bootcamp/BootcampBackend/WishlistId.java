package io.bootcamp.BootcampBackend;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class WishlistId implements Serializable {

    @Column(name = "user_id")
    private int userId;

    @Column(name = "course_id")
    private int courseId;

    public WishlistId() {
    }

    public WishlistId(int userId, int courseId) {
        this.userId = userId;
        this.courseId = courseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "WishlistId{" +
                "userId=" + userId +
                ", courseId=" + courseId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishlistId that = (WishlistId) o;
        return userId == that.userId && courseId == that.courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, courseId);
    }
}
