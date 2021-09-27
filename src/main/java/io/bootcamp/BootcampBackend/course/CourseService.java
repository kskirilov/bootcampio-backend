package io.bootcamp.BootcampBackend.course;

import io.bootcamp.BootcampBackend.exception.NotFoundException;
import io.bootcamp.BootcampBackend.user.User;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements CourseManagement{
    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    @Override
    public void addNewCourse(CourseDTO courseDTO) {
        Course course = new Course();
        mapDTOtoEntity(courseDTO, course);

        Course result = courseRepository.save(course);

        if (!course.equals(result)){
            throw new IllegalStateException("oops something went wrong with the database");
        }
    }

    @Override
    public void updateCourse(CourseDTO courseDTO) {
        if (courseDTO == null || courseDTO.getId() == 0) {
            throw new NotFoundException("Missing Data Exception");}


        if (!courseRepository.findById(courseDTO.getId()).isEmpty()){
            Course course = courseRepository.getById(courseDTO.getId());
            mapDTOtoEntity(courseDTO, course);
            courseRepository.save(course);
        } else{
            throw new NotFoundException("Course not found");
        }

    }

    @Override
    public void deleteCourseById(int id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty()) {
            throw new NotFoundException("Course does not exist");
        }

        courseRepository.deleteById(id);

    }

    @Override
    public List<CourseDTO> selectAllCourses(String input) {
        List<Course> coursesInRepo = courseRepository.findAll(Sort.by(input));

        List<CourseDTO> courses = new ArrayList<>();

        coursesInRepo.stream().forEach(
                c -> {
                    CourseDTO courseDTO = mapEntityToDTO(c);
                    courses.add(courseDTO);
                }
        );


        return courses;
    }

    @Override
    public CourseDTO selectCourseById(int id) {
        Optional<Course> course = courseRepository.findById(id);

        if (course.isEmpty()){
            throw new NotFoundException("Course does not exist");
        }

        CourseDTO courseDTO = mapEntityToDTO(course.get());
        return courseDTO;
    }

    private void mapDTOtoEntity(CourseDTO courseDTO, Course course){
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        course.setCategory(courseDTO.getCategory());
        course.setSubcategory(courseDTO.getSubcategory());
        course.setDeadline(courseDTO.getDeadline());
        course.setCost(courseDTO.getCost());
        course.setLocation(courseDTO.getLocation());
        course.setPlace(courseDTO.getPlace());
        course.setSpacesAvailable(courseDTO.getSpacesAvailable());
        course.setSignUpThrough(courseDTO.getSignUpThrough());

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
