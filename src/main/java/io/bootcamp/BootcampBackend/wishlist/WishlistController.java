package io.bootcamp.BootcampBackend.wishlist;

import io.bootcamp.BootcampBackend.course.Course;
import io.bootcamp.BootcampBackend.course.CourseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/wishlist")
public class WishlistController {
    private final WishlistService wishlistService;
    public WishlistController(WishlistService wishlistService){
        this.wishlistService = wishlistService;
    }

    @GetMapping("{id}")
    public List<CourseDTO> selectWishListByUserID(@PathVariable("id") int userID){
        return wishlistService.selectWishlistByUserId(userID);
    }

    @PostMapping("{id}")
    public void addCourseIntoWishList(@PathVariable("id") int userID, @RequestParam int courseID ){
        wishlistService.addCourseIntoWishlist(courseID, userID);
    }

    @DeleteMapping("{id}")
    public void deleteCourseFromWishList(@PathVariable("id") int userID, @RequestParam int courseID ){
        wishlistService.deleteCourseFromWishlist(courseID, userID);
    }
}
