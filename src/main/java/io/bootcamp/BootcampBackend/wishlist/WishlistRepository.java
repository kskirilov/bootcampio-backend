package io.bootcamp.BootcampBackend.wishlist;

import io.bootcamp.BootcampBackend.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    @Query("SELECT w.course FROM Wishlist w WHERE w.user.id =:userId")
    List<Course> findWishlistByUserId(@Param("userId") int userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Wishlist w WHERE w.course.id =:courseId AND w.user.id =:userId")
    int deleteWishlist(@Param("courseId") int courseId, @Param("userId") int userId);
}
