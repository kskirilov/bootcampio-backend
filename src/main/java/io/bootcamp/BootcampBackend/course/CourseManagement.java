package io.bootcamp.BootcampBackend.course;

import java.util.List;

public interface CourseManagement {
    void addNewCourse(CourseDTO courseDTO);
    void updateCourse(CourseDTO courseDTO);
    void deleteCourseById(int id);
    List<CourseDTO> selectAllCourses(String input);
    CourseDTO selectCourseById(int id);
}
