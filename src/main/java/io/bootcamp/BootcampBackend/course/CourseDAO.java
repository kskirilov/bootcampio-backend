package io.bootcamp.BootcampBackend.course;

import io.bootcamp.BootcampBackend.user.User;

import java.util.List;
import java.util.Optional;

public interface CourseDAO {
    // DAO = The Data Access Object (DAO) pattern is a structural pattern that allows us
    // to isolate the application/business layer from the persistence layer (usually a relational database,
    // but it could be any other persistence mechanism) using an abstract API.

    Optional<Course> selectCourseById(int id);
    List<Course> selectAllCourses();
    int deleteCourse(int id);
    int insertCourse (Course course);
    int updateCourse(Course course);
    //update users set num_points = 6000 where id = 2;
}