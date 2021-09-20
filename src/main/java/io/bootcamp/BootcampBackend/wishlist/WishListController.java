//package io.bootcamp.BootcampBackend.wishlist;
//
//import io.bootcamp.BootcampBackend.course.Course;
//import io.bootcamp.BootcampBackend.exception.AlreadyExistsException;
//import io.bootcamp.BootcampBackend.util.Response;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(path = "api/wishlist")
//public class WishListController{
//    // CALLS BUSINESS LOGIC
//
//    private WishListService wishListService;
//
//    public WishListController(WishListService wishListService){
//        this.wishListService = wishListService;
//    }
//
//    @GetMapping("{id}")
//    public List<Course> selectWishListByUserID(@PathVariable("id") int userID){
//        return wishListService.selectWishListByUserID(userID);
//    }
//
//    @PostMapping("{id}")
//    public Response addCourseIntoWishList(@PathVariable("id") int userID, @RequestParam int courseID ){
//        return wishListService.addCourseIntoWishList(courseID, userID);
//    }
//
//    @DeleteMapping("{id}")
//    public Response deleteCourseFromWishList(@PathVariable("id") int userID, @RequestParam int courseID ){
//        return wishListService.deleteCourseFromWishList(courseID, userID);
//    }
//}