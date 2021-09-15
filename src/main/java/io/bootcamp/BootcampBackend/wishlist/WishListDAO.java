package io.bootcamp.BootcampBackend.wishlist;

import io.bootcamp.BootcampBackend.course.Course;

import java.util.List;

public interface WishListDAO {
    List<Course> selectWishListByUserID(int userID);
    int insertIntoWishlist(int CourseID, int userID);
    int deleteFromWishList(int CourseID, int userID);
}
