package io.bootcamp.BootcampBackend.wishlist;

import io.bootcamp.BootcampBackend.course.Category;
import io.bootcamp.BootcampBackend.course.Course;
import io.bootcamp.BootcampBackend.course.Location;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishlistDataAccessService implements WishListDAO{
    // Makes the calls to the databases (CALLED FROM WishListService)



    //wishlist is a table which stores the user_id and course_id whenever a user adds a course to their wishlist
    //it has a select, insert and delete function
    //       WISHLIST TABLE
    //id | course_id | user_id
    //1  |      2    |    4
    //2  |      1    |    3

    JdbcTemplate jdbcTemplate;

    public WishlistDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Course> selectWishListByUserID(int userID) {
        String sql = """
                SELECT courses.id, courses.name, courses.rating, courses.description, categories.name, subcategories.name, courses.deadline, courses.cost, courses.location, courses.place, courses.spaces_available, courses.sign_up_through,
                categories.name AS category_name,
                subcategories.name AS subcategory_name
                
                FROM courses
                
                INNER JOIN categories
                ON courses.category_id = categories.id
                INNER JOIN subcategories
                ON courses.subcategory_id = subcategories.id
                INNER JOIN wishlist
                ON wishlist.course_id = courses.id
                
                WHERE wishlist.user_id = ?
                """;

        List<Course> result = jdbcTemplate.query(sql, getCourseRowMapper(), userID);

        return result;
    }

    @Override
    public int insertIntoWishlist(int courseID, int userID) {
        String sql = """
                INSERT INTO wishlist(course_id, user_id) VALUES(?, ?)
                """;

        int result = jdbcTemplate.update(sql, courseID, userID);

        return result;
    }

    @Override
    public int deleteFromWishList(int courseID, int userID) {
        String sql = """
                DELETE FROM wishlist WHERE course_id = ? AND user_id = ?
                """;

        int result = jdbcTemplate.update(sql, courseID, userID);

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
