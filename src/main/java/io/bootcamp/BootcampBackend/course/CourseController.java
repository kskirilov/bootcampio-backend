package io.bootcamp.BootcampBackend.course;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/courses")

public class CourseController {

    private  CourseService courseService;
    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @PostMapping
    public void addCourse(@RequestBody Course course){
        courseService.addNewCourse(course);
    }
}