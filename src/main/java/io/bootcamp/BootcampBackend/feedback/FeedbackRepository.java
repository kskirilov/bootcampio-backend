package io.bootcamp.BootcampBackend.feedback;

import io.bootcamp.BootcampBackend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    @Query("SELECT f FROM Feedback f WHERE f.courseId.id = :courseId AND f.userId.id = :userId")
    List<Feedback> selectAllFeedbackByCourseAndUser(@Param("courseId") int courseId,
                                             @Param("userId") int userId);

    @Query("SELECT f FROM Feedback f WHERE f.userId.id = :userId")
    List<Feedback> selectAllFeedbackByUser(@Param("userId") int userId);

    @Query("SELECT f FROM Feedback f WHERE f.courseId.id = :courseId")
    List<Feedback> selectAllFeedbackForCourse(@Param("courseId")int courseId);

    @Query("SELECT AVG(f.courseRating) FROM Feedback f WHERE f.courseId.id =:courseId")
    double calculateCourseAverageRating(@Param("courseId")int courseId);


}
