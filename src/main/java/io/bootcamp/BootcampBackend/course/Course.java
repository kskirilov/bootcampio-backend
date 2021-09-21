package io.bootcamp.BootcampBackend.course;

import io.bootcamp.BootcampBackend.wishlist.Wishlist;
import io.bootcamp.BootcampBackend.feedback.Feedback;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(name = "courses")
@Entity(name = "Course")
public class Course {
    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private int id;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(
            name = "rating",
            nullable = false
    )
    private double rating;

    @Column(
            name = "description",
            nullable = false
    )
    private String description;

    @Column(
            name = "category",
            nullable = false
    )
    private Category category;

    @Column(
            name = "subcategory"
    )
    private String subcategory;

    @Column(
            name = "deadline",
            nullable = false
    )
    private LocalDate deadline;

    @Column(
            name = "cost",
            nullable = false
    )
    private double cost;

    @Column(
            name = "location",
            nullable = false
    )
    private Location location;

    @Column(
            name = "place",
            nullable = false
    )
    private String place;

    @Column(
            name = "spaces_available",
            nullable = false
    )
    private int spacesAvailable;

    @Column(
            name = "sign_up_through",
            nullable = false
    )
    private String signUpThrough;

    @OneToMany(
            mappedBy = "courseId",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private List<Feedback> feedback = new ArrayList<>();

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "course"
    )
    private List<Wishlist> wishlists = new ArrayList<>();

    public Course(){};

    public Course(String name, double rating, String description, Category category, String subcategory, LocalDate deadline, double cost, Location location, String place, int spacesAvailable, String signUpThrough) {
        this.name = name;
        this.rating = rating;
        this.description = description;
        this.category = category;
        this.subcategory = subcategory;
        this.deadline = deadline;
        this.cost = cost;
        this.location = location;
        this.place = place;
        this.spacesAvailable = spacesAvailable;
        this.signUpThrough = signUpThrough;
    }



    //GETTERS AND SETTERS TO ACCESS EACH PROPERTY IN COURSES TO interact with DB (add, retrieve, insert, delete) later

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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getSpacesAvailable() {
        return spacesAvailable;
    }

    public void setSpacesAvailable(int spacesAvailable) {
        this.spacesAvailable = spacesAvailable;
    }

    public String getSignUpThrough() {
        return signUpThrough;
    }

    public void setSignUpThrough(String signUpThrough) {
        this.signUpThrough = signUpThrough;
    }

    public void addFeedback(Feedback feedback){
        if(!this.feedback.contains(feedback)){
            this.feedback.add(feedback);
            feedback.setCourseId(this);
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

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", subcategory='" + subcategory + '\'' +
                ", deadline=" + deadline +
                ", cost=" + cost +
                ", location=" + location +
                ", place='" + place + '\'' +
                ", spacesAvailable=" + spacesAvailable +
                ", signUpThrough='" + signUpThrough + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        //allows you to compare 2 objects' values (rather than their references)

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && Double.compare(course.rating, rating) == 0 && Double.compare(course.cost, cost) == 0 && spacesAvailable == course.spacesAvailable && Objects.equals(name, course.name) && Objects.equals(description, course.description) && category == course.category && Objects.equals(subcategory, course.subcategory) && Objects.equals(deadline, course.deadline) && location == course.location && Objects.equals(place, course.place) && Objects.equals(signUpThrough, course.signUpThrough);
    }

    @Override
    public int hashCode() {
        //returns distinct integers for different objects)

        return Objects.hash(id, name, rating, description, category, subcategory, deadline, cost, location, place, spacesAvailable, signUpThrough);
    }
}
