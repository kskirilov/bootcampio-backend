package io.bootcamp.BootcampBackend.wishlist;

import io.bootcamp.BootcampBackend.course.Course;
import io.bootcamp.BootcampBackend.course.CourseService;
import io.bootcamp.BootcampBackend.exception.AlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {
    private final WishListDAO wishListDAO;
    private final CourseService courseService;

    public WishListService(WishListDAO wishListDAO, CourseService courseService){

        this.wishListDAO = wishListDAO;
        this.courseService = courseService;
    }

    public List<Course> selectWishListByUserID(int userID){
        return wishListDAO.selectWishListByUserID(userID);
    }

    public void addCourseIntoWishList(int courseID, int userID){
        courseService.selectCourseById(courseID);

        if (selectWishListByUserID(userID).stream().anyMatch(c -> c.getId() == courseID)){
            throw new AlreadyExistsException("Course already on wishlist");
        }

        wishListDAO.insertIntoWishlist(courseID, userID);
    }

}
