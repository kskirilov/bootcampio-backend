package io.bootcamp.BootcampBackend.course;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/courses")
public class CourseController {
    private CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseDTO> showAllCourses(@RequestParam(defaultValue = "id") String sort){
        return courseService.selectAllCourses(sort);
    }

    @PostMapping
    public void addCourse(@RequestBody CourseDTO courseDTO){
        courseService.addNewCourse(courseDTO);
    }

    @GetMapping("{id}")
    public CourseDTO selectCourseById(@PathVariable("id") int id){
        return courseService.selectCourseById(id);
    }

    @PutMapping
    public void updateCourse(@RequestBody CourseDTO courseDTO){
        courseService.updateCourse(courseDTO);
    }

    @DeleteMapping("{id}")
    public void deleteCourseById(@PathVariable("id") int id){
        courseService.deleteCourseById(id);
    }
}