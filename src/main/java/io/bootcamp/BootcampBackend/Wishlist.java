package io.bootcamp.BootcampBackend;

import io.bootcamp.BootcampBackend.course.Course;
import io.bootcamp.BootcampBackend.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Wishlist")
@Table(name = "wishlist")
public class Wishlist {

    @EmbeddedId
    private WishlistId wishlistId = new WishlistId();

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(
            name = "user_id",
            foreignKey = @ForeignKey(
                    name = "fk_wishlist_user_id"
            )
    )
    private User user;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(
            name = "course_id",
            foreignKey = @ForeignKey(
                   name = "fk_wishlist_course_id"
           )
    )
    private Course course;

    @Column(
            name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime createdAt;

    public Wishlist() {
    }

    public Wishlist(User user, Course course, LocalDateTime createdAt) {
        this.user = user;
        this.course = course;
        this.createdAt = createdAt;
    }

    public WishlistId getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(WishlistId wishlistId) {
        this.wishlistId = wishlistId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
