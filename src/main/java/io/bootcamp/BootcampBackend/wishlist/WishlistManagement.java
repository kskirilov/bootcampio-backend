package io.bootcamp.BootcampBackend.wishlist;

import io.bootcamp.BootcampBackend.course.CourseDTO;

import java.util.List;

public interface WishlistManagement {
    List<CourseDTO> selectWishlistByUserId(int userId);
    void addCourseIntoWishlist(int courseId, int userId);
    void deleteCourseFromWishlist(int courseId, int userId);

}
