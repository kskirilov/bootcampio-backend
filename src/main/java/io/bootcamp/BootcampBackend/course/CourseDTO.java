package io.bootcamp.BootcampBackend.course;

import java.time.LocalDate;
import java.util.Objects;

public class CourseDTO {
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

    public CourseDTO(){};

    public CourseDTO(int id, String name, double rating, String description, Category category, String subcategory, LocalDate deadline, double cost, Location location, String place, int spacesAvailable, String signUpThrough) {
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
        return "CourseDTO{" +
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseDTO courseDTO = (CourseDTO) o;
        return id == courseDTO.id && Double.compare(courseDTO.rating, rating) == 0 && Double.compare(courseDTO.cost, cost) == 0 && spacesAvailable == courseDTO.spacesAvailable && Objects.equals(name, courseDTO.name) && Objects.equals(description, courseDTO.description) && category == courseDTO.category && Objects.equals(subcategory, courseDTO.subcategory) && Objects.equals(deadline, courseDTO.deadline) && location == courseDTO.location && Objects.equals(place, courseDTO.place) && Objects.equals(signUpThrough, courseDTO.signUpThrough);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rating, description, category, subcategory, deadline, cost, location, place, spacesAvailable, signUpThrough);
    }
}
