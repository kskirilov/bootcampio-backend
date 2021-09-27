package io.bootcamp.BootcampBackend.wishlist;

import io.bootcamp.BootcampBackend.course.Course;
import io.bootcamp.BootcampBackend.course.CourseDTO;
import io.bootcamp.BootcampBackend.course.CourseRepository;
import io.bootcamp.BootcampBackend.exception.AlreadyExistsException;
import io.bootcamp.BootcampBackend.exception.NotFoundException;
import io.bootcamp.BootcampBackend.user.User;
import io.bootcamp.BootcampBackend.user.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistService implements WishlistManagement{
    private final WishlistRepository wishlistRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public WishlistService(WishlistRepository wishlistRepository,
                           CourseRepository courseRepository,
                           UserRepository userRepository){
        this.wishlistRepository = wishlistRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<CourseDTO> selectWishlistByUserId(int userId) {
        List<Course> courseInRepo = wishlistRepository.findWishlistByUserId(userId);
        List<CourseDTO> courses = new ArrayList<>();

        courseInRepo.stream().forEach(c -> {
            CourseDTO courseDTO = mapEntityToDTO(c);
            courses.add(courseDTO);
        });

        return courses;
    }

    @Override
    public void addCourseIntoWishlist(int courseID, int userID){

        if (selectWishlistByUserId(userID).stream().anyMatch(c -> c.getId() == courseID)){
            throw new AlreadyExistsException("Course already in wishlist");
        }

        Course course = courseRepository.findById(courseID).get();
        User user = userRepository.findById(userID).get();
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlist.setCourse(course);
        wishlist.setCreatedAt(LocalDateTime.now());
        wishlistRepository.save(wishlist);
    }

    @Override
    public void deleteCourseFromWishlist(int courseID, int userID){

        if (!selectWishlistByUserId(userID).stream().anyMatch(c -> c.getId() == courseID)){
            throw new NotFoundException("Course not found in wishlist");
        }

        wishlistRepository.deleteWishlist(courseID, userID);

    }

    private CourseDTO mapEntityToDTO(Course course){
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setRating(course.getRating());
        courseDTO.setDescription(course.getDescription());
        courseDTO.setCategory(course.getCategory());
        courseDTO.setSubcategory(course.getSubcategory());
        courseDTO.setDeadline(course.getDeadline());
        courseDTO.setCost(course.getCost());
        courseDTO.setLocation(course.getLocation());
        courseDTO.setPlace(course.getPlace());
        courseDTO.setSpacesAvailable(course.getSpacesAvailable());
        courseDTO.setSignUpThrough(course.getSignUpThrough());
        return courseDTO;
    }

}
