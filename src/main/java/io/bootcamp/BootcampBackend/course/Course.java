package io.bootcamp.BootcampBackend.course;

import java.time.LocalDate;
import java.util.Objects;

public class Course {
    //                                                             Courses table
    // id |                  name                    | rating   |   description      |  category             | subcategory |  deadline    |  cost  |  location | place | spacesAvailable |  signUpThrough
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // 1  | Bright Network Technology Academy (BNTA) |   4.9    |   course is cool   |  SOFTWARE_ENGINEERING | FULL_STACK  |  15/09/2021  |  0.00  |  Online   | Zoom  |       40        |  https://www.brightnetwork.co.uk/graduate-employer-company/bright-network-technology-academy/

    private int id;
    private String name;
    private double rating;
    private String description;
    private Category category;
    private String subcategory;
    private LocalDate deadline;
    private double cost;
    private Location location;
    private String place;
    private int spacesAvailable;
    private String signUpThrough;

    public Course(int id, String name, double rating, String description, Category category, String subcategory, LocalDate deadline, double cost, Location location, String place, int spacesAvailable, String signUpThrough) {
        this.id = id;
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
