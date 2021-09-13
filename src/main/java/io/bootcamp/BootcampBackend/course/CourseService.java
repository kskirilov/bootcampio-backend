package io.bootcamp.BootcampBackend.course;

import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseDAO courseDAO;

    public CourseService(CourseDataAccessService courseDataAccessService) {
        this.courseDAO = courseDataAccessService;
    }

    public void addNewCourse(Course course){
        // TODO: check if user exists
        int result = courseDAO.insertCourse(course);
        if (result != 1) {
            throw new IllegalStateException("oops something went wrong");
        }
    }
}