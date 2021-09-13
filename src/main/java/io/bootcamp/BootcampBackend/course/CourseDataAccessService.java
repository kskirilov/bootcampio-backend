package io.bootcamp.BootcampBackend.course;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CourseDataAccessService implements CourseDAO {
    //provides the functionality to select a course, select all courses, delete a course and add a course.

    private JdbcTemplate jdbcTemplate;

    public CourseDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Course> selectCourseById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Course> selectAllCourses() {
        return null;
    }

    @Override
    public int deleteCourse(int id) {
        return 0;
    }

    @Override
    public int insertCourse(Course course) {

        String subcategoryIdSql = """
                SELECT * FROM subcategories WHERE name = ?
                """;
        
        String insertCourseSql = """
                INSERT INTO courses(name, rating, description, category_id, subcategory_id, deadline, cost, location, place, spaces_available, sign_up_through) 
                VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        String location = String.valueOf(course.getLocation());

        List<Map<String, Object>> subcategoryResult = jdbcTemplate.queryForList(subcategoryIdSql, course.getSubcategory());

        int result = jdbcTemplate.update(insertCourseSql, course.getName(), course.getRating(), course.getDescription(), subcategoryResult.get(0).get("category_id"), subcategoryResult.get(0).get("id"), course.getDeadline(), course.getCost(), location, course.getPlace(), course.getSpacesAvailable(), course.getSignUpThrough());

        return result;
    }


}