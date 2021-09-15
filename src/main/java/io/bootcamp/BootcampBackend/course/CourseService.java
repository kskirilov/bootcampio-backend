package io.bootcamp.BootcampBackend.course;

import io.bootcamp.BootcampBackend.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseDAO courseDAO;

    public CourseService(CourseDataAccessService courseDataAccessService) {
        this.courseDAO = courseDataAccessService;
    }

    public void addNewCourse(Course course){
        int result = courseDAO.insertCourse(course);
        if (result != 1) {
            throw new IllegalStateException("oops something went wrong");
        }
    }

    public List<Course> selectAllCourses() {
        return courseDAO.selectAllCourses();
    }

    public Course selectCourseById(int id){
        return courseDAO.selectCourseById(id).orElseThrow(() -> new NotFoundException("Course not found"));
    }

    public void updateCourse(Course course){
        selectCourseById(course.getId());

        int result = courseDAO.updateCourse(course);
        if (result != 1) {
            throw new IllegalStateException("oops something went wrong in the database");
        }

    }

    public void deleteCourse(int id){
        int result = courseDAO.deleteCourse(id);
        if (result != 1) {
            throw new IllegalStateException("oops something went wrong in the database");
        }
    }
}