//package io.bootcamp.BootcampBackend.wishlist;
//
//import io.bootcamp.BootcampBackend.course.Course;
//import io.bootcamp.BootcampBackend.course.CourseService;
//import io.bootcamp.BootcampBackend.exception.AlreadyExistsException;
//import io.bootcamp.BootcampBackend.exception.NotFoundException;
//import io.bootcamp.BootcampBackend.util.Response;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class WishListService {
//    // BUSINESS LOGIC
//    // CALLS DataAccessService
//
//    private final WishListDAO wishListDAO;
//    private final CourseService courseService;
//
//    public WishListService(WishListDAO wishListDAO, CourseService courseService){
//
//        this.wishListDAO = wishListDAO;
//        this.courseService = courseService;
//
//    }
//
//    public List<Course> selectWishListByUserID(int userID){
//        return wishListDAO.selectWishListByUserID(userID);
//    }
//
//    public Response addCourseIntoWishList(int courseID, int userID){
//        Course course = courseService.selectCourseById(courseID);
//
//        if (selectWishListByUserID(userID).stream().anyMatch(c -> c.getId() == courseID)){
//            throw new AlreadyExistsException("Course already in wishlist");
//        }
//
//        wishListDAO.insertIntoWishlist(courseID, userID);
//
//        return new Response(courseID, course.getName() + " added to wishlist");
//    }
//
//    public Response deleteCourseFromWishList(int courseID, int userID){
//        Course course = courseService.selectCourseById(courseID);
//
//        if (!selectWishListByUserID(userID).stream().anyMatch(c -> c.getId() == courseID)){
//           throw new NotFoundException("Course not found in wishlist");
//        }
//
//        wishListDAO.deleteFromWishList(courseID, userID);
//
//        return new Response(courseID, course.getName() + " deleted from database");
//    }
//
//}
