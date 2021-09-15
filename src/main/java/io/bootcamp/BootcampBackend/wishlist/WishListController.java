package io.bootcamp.BootcampBackend.wishlist;

import io.bootcamp.BootcampBackend.course.Course;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/wishlist")
public class WishListController{
    // CALLS BUSINESS LOGIC

    private WishListService wishListService;

    public WishListController(WishListService wishListService){
        this.wishListService = wishListService;
    }

    @GetMapping
    public List<Course> selectWishListByUserID(@RequestParam int userID){
        return wishListService.selectWishListByUserID(userID);
    }

    @PostMapping
    public void addCourseIntoWishList(@RequestParam int courseID, @RequestParam int userID ){
        wishListService.addCourseIntoWishList(courseID, userID);
    }

    @DeleteMapping
    public void deleteCourseFromWishList(@RequestParam int courseID, @RequestParam int userID ){
        wishListService.deleteCourseFromWishList(courseID, userID);
    }
}