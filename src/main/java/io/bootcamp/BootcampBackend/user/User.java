package io.bootcamp.BootcampBackend.user;
import io.bootcamp.BootcampBackend.wishlist.Wishlist;
import io.bootcamp.BootcampBackend.feedback.Feedback;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "User")
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "user_email_unique", columnNames = "email")
        }
)
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private int id;

    @Column(
            name = "name",
            nullable = false
//            columnDefinition = "VARCHAR(255)"
    )
    private String name;

    @Column(
            name = "email",
            nullable = false
//            columnDefinition = "VARCHAR(255)"
    )
    private String email;

    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "CHAR(60)"
    )
    private String password;

    @Column(
            name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(
            name = "updated_at",
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime updatedAt;

    @Column(
            name = "last_seen",
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime lastSeen;

    @OneToMany(
            mappedBy = "userId",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<Feedback> feedback = new ArrayList<>();

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "user"
    )
    private List<Wishlist> wishlists = new ArrayList<>();

    public User(){};
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password, LocalDateTime updatedAt) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(LocalDateTime lastSeen) {
        this.lastSeen = lastSeen;
    }

    public void addFeedback(Feedback feedback){
        if(!this.feedback.contains(feedback)){
            this.feedback.add(feedback);
            feedback.setUserId(this);
        }
    }

    public void removeFeedback(Feedback feedback){
        if(this.feedback.contains(feedback)){
            this.feedback.remove(feedback);
            feedback.setUserId(null);
        }
    }

    public List<Feedback> getFeedback(){
        return feedback;
    }

    public List<Wishlist> getWishlists(){
        return wishlists;
    }
    public void addToWishlist(Wishlist wishlist){
        if(!wishlists.contains(wishlist)){
            wishlists.add(wishlist);
        }
    }

    public void removeFromWishlist(Wishlist wishlist){
        wishlists.remove(wishlist);
    }

    public void deleteWishlist(){
        wishlists = null;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", lastSeen=" + lastSeen +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(createdAt, user.createdAt) && Objects.equals(updatedAt, user.updatedAt) && Objects.equals(lastSeen, user.lastSeen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, createdAt, updatedAt, lastSeen);
    }
}
