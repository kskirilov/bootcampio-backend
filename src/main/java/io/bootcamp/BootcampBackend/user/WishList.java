package io.bootcamp.BootcampBackend.user;

import io.bootcamp.BootcampBackend.course.Course;

import java.util.List;
import java.util.Objects;

public class WishList {
    private List<Course> courses;

    public WishList(List<Course> courses) {
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishList wishList = (WishList) o;
        return Objects.equals(courses, wishList.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courses);
    }

    @Override
    public String toString() {
        return "WishList{" +
                "courses=" + courses +
                '}';
    }
}
