package io.bootcamp.BootcampBackend.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE Course c SET c.rating =:rating WHERE c.id =:courseId")
    int updateCourseRating(@Param("courseId")int id, @Param("rating") double rating);
}
