package io.bootcamp.BootcampBackend.user;

import io.bootcamp.BootcampBackend.course.Course;

import java.util.List;
import java.util.Objects;

public class WishList {
    //          WISHLIST TABLE
    // id | courseID | userID | dateAdded
    // 1  |  1       |  1
    // 2  |  2       |  1
    // 3  |  3       |  1

    private int id;
    private int courseID;
    private int userID;

    public WishList(int id, int courseID, int userID) {
        this.id = id;
        this.courseID = courseID;
        this.userID = userID;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishList wishList = (WishList) o;
        return id == wishList.id && courseID == wishList.courseID && userID == wishList.userID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseID, userID);
    }

    @Override
    public String toString() {
        return "WishList{" +
                "id=" + id +
                ", courseID=" + courseID +
                ", userID=" + userID +
                '}';
    }
}