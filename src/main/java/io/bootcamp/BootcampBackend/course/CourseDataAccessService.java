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
                LEFT JOIN subcategories
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
                LEFT JOIN subcategories
                ON courses.subcategory_id = subcategories.id
                """;

        List<Course> result = jdbcTemplate.query(selectAllCourses, getCourseRowMapper());

        return result;

    }



    @Override
    public int deleteCourse(int id) {
        String sql = """
                DELETE FROM courses WHERE id = ?
                """;

        int result = jdbcTemplate.update(sql, id);

        return result;
    }

    @Override
    public int updateCourse(Course course) {
        int result = 0;

        String getCategoryIdSql = """
                SELECT id FROM categories WHERE name = ?
                """;

        List<Map<String, Object>> categoryIdResult = jdbcTemplate.queryForList(getCategoryIdSql, course.getCategory().toString());

        int categoryId = (int) categoryIdResult.get(0).get("id");


        if (course.getSubcategory() != null){
            String getSubcategoryIdSql = """
                SELECT id FROM subcategories WHERE name = ?
                """;
            List<Map<String, Object>> subcategoryIdResult = jdbcTemplate.queryForList(getSubcategoryIdSql, course.getSubcategory());

            int subcategoryId = (int) subcategoryIdResult.get(0).get("id");

            String sql = """
                        UPDATE courses SET name = ?, rating = ?, description = ?, category_id = ?,
                            subcategory_id = ?, deadline = ?, cost = ?, location = CAST(? AS location_type), 
                            place = ?, spaces_available = ?, sign_up_through = ? 
                     
         """;

            result = jdbcTemplate.update(sql, course.getName(), course.getRating(), course.getDescription(),
                    categoryId, subcategoryId, course.getDeadline(),
                    course.getCost(),course.getLocation().toString(),course.getPlace(),
                    course.getSpacesAvailable(),course.getSignUpThrough(), course.getId());

        } else {

            String sql = """
                        UPDATE courses SET name = ?, rating = ?, description = ?, category_id = ?, 
                            subcategory_id = null,
                            deadline = ?, cost = ?, location = CAST( ? AS location_type) , 
                            place = ?, spaces_available = ?, sign_up_through = ? 
                            
                            WHERE id = ?
                           
                     
         """;

            result = jdbcTemplate.update(sql, course.getName(), course.getRating(), course.getDescription(),
                    categoryId, course.getDeadline(),
                    course.getCost(),course.getLocation().toString(),course.getPlace(),
                    course.getSpacesAvailable(),course.getSignUpThrough(), course.getId());

        }


        return result;
    }

    @Override
    public int insertCourse(Course course) {
        int result = 0;

        String getCategoryIdSql = """
                SELECT id FROM categories WHERE name = ?
                """;

        List<Map<String, Object>> categoryIdResult = jdbcTemplate.queryForList(getCategoryIdSql, course.getCategory().toString());

        int categoryId = (int) categoryIdResult.get(0).get("id");

        if(course.getSubcategory() != null){
            String getSubcategoryIdSql = """
                SELECT id FROM subcategories WHERE name = ?
                """;

            List<Map<String, Object>> subcategoryResult = jdbcTemplate.queryForList(getSubcategoryIdSql, course.getSubcategory());

            int subcategoryId = (int) subcategoryResult.get(0).get("id");

            String insertCourseSql = """
                INSERT INTO courses(name, rating, description, category_id, subcategory_id, deadline, cost, location, place, spaces_available, sign_up_through) 
                VALUES(?, ?, ?, ?, ?, ?, ?, CAST(? AS location_type), ?, ?, ?)
                """;


            result = jdbcTemplate.update(insertCourseSql, course.getName(), course.getRating(), course.getDescription(), categoryId, subcategoryId, course.getDeadline(), course.getCost(), course.getLocation().toString(), course.getPlace(), course.getSpacesAvailable(), course.getSignUpThrough());

        } else{
            String insertCourseSql = """
                INSERT INTO courses(name, rating, description, category_id, subcategory_id , deadline, cost, location, place, spaces_available, sign_up_through) 
                VALUES(?, ?, ?, ?, null, ?, ?, CAST(? AS location_type), ?, ?, ?)
                """;


            result = jdbcTemplate.update(insertCourseSql, course.getName(), course.getRating(), course.getDescription(), categoryId, course.getDeadline(), course.getCost(), course.getLocation().toString(), course.getPlace(), course.getSpacesAvailable(), course.getSignUpThrough());

        }


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