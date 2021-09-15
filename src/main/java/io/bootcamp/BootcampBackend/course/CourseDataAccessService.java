package io.bootcamp.BootcampBackend.course;

import io.bootcamp.BootcampBackend.user.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
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
        String sql = """
                SELECT courses.id, courses.name, courses.rating, courses.description, categories.name, subcategories.name, courses.deadline, courses.cost, courses.location, courses.place, courses.spaces_available, courses.sign_up_through,
                categories.name AS category_name,
                subcategories.name AS subcategory_name
                
                FROM courses 
                
                INNER JOIN categories
                ON courses.category_id = categories.id
                INNER JOIN subcategories
                ON courses.subcategory_id = subcategories.id
                
                WHERE courses.id = ?
                
                """;

        List<Course> courses = jdbcTemplate.query(sql, getCourseRowMapper(), id);

        if(courses.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(courses.get(0));
    }

    @Override
    public List<Course> selectAllCourses() {

        String selectAllCourses = """
                SELECT courses.id, courses.name, courses.rating, courses.description, categories.name, subcategories.name, courses.deadline, courses.cost, courses.location, courses.place, courses.spaces_available, courses.sign_up_through,
                categories.name AS category_name,
                subcategories.name AS subcategory_name
                
                FROM courses 
                
                INNER JOIN categories
                ON courses.category_id = categories.id
                INNER JOIN subcategories
                ON courses.subcategory_id = subcategories.id
                """;

        List<Course> result = jdbcTemplate.query(selectAllCourses, getCourseRowMapper());

        return result;

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
                VALUES(?, ?, ?, ?, ?, ?, ?, CAST(? AS location_type), ?, ?, ?)
                """;

        List<Map<String, Object>> subcategoryResult = jdbcTemplate.queryForList(subcategoryIdSql, course.getSubcategory());

        int result = jdbcTemplate.update(insertCourseSql, course.getName(), course.getRating(), course.getDescription(), subcategoryResult.get(0).get("category_id"), subcategoryResult.get(0).get("id"), course.getDeadline(), course.getCost(), course.getLocation().toString(), course.getPlace(), course.getSpacesAvailable(), course.getSignUpThrough());

        return result;
    }


    private RowMapper<Course> getCourseRowMapper() {
        return (resultSet, i) -> {
            return new Course(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getDouble("rating"),
                    resultSet.getString("description"),
                    Category.valueOf(resultSet.getString("category_name")),
                    resultSet.getString("subcategory_name"),
                    resultSet.getDate("deadline").toLocalDate(),
                    resultSet.getInt("cost"),
                    Location.valueOf(resultSet.getString("location")),
                    resultSet.getString("place"),
                    resultSet.getInt("spaces_available"),
                    resultSet.getString("sign_up_through"));
        };
    }

}