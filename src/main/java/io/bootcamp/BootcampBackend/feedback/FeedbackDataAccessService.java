//package io.bootcamp.BootcampBackend.feedback;
//
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Repository;
//
//import java.math.BigDecimal;
//import java.text.DecimalFormat;
//import java.util.List;
//import java.util.Map;
//
//@Repository
//public class FeedbackDataAccessService implements FeedbackDAO{
//    JdbcTemplate jdbcTemplate;
//    public FeedbackDataAccessService(JdbcTemplate jdbcTemplate){
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    public List<Feedback> selectAllFeedback() {
//        String sql = """
//                SELECT * FROM feedback
//                """ ;
//
//        List<Feedback> result = jdbcTemplate.query(sql, getFeedbackRowMapper());
//        return result;
//    }
//
//    @Override
//    public List<Feedback> selectAllFeedbackForCourse(int courseId) {
//        String sql = """
//                SELECT * FROM feedback WHERE course_id = ?
//                """;
//
//        List<Feedback> result = jdbcTemplate.query(sql, getFeedbackRowMapper(), courseId);
//        return result;
//    }
//
//    @Override
//    public List<Feedback> selectAllFeedbackByUser(int userId) {
//        String sql = """
//                SELECT * FROM feedback WHERE user_id = ?
//                """;
//
//        List<Feedback> result = jdbcTemplate.query(sql, getFeedbackRowMapper(), userId);
//        return result;
//    }
//
//    @Override
//    public List<Feedback> selectAllFeedbackByUserAndCourse(int courseId, int userId) {
//        String sql = """
//                SELECT * FROM feedback WHERE course_id = ? AND user_id = ?
//                """;
//
//        List<Feedback> result = jdbcTemplate.query(sql, getFeedbackRowMapper(), courseId, userId);
//        return result;
//    }
//
//    @Override
//    public int insertFeedback(Feedback feedback) {
//        String sql = """
//                INSERT INTO feedback(course_id, user_id, course_rating, comment)
//                VALUES(?, ?, ?, ?)
//                """;
//
//        int result = jdbcTemplate.update(sql, feedback.getCourseId(), feedback.getUserId(), feedback.getCourseRating(), feedback.getComment());
//        updateCourseRating(feedback.getCourseId());
//
//        return result;
//    }
//
//    @Override
//    public int updateFeedback(Feedback feedback) {
//        String sql = """
//                UPDATE feedback SET course_rating = ?, comment = ? WHERE course_id = ?, user_id = ?
//                """;
//
//        int result = jdbcTemplate.update(sql, feedback.getCourseId(), feedback.getUserId(), feedback.getCourseRating(), feedback.getComment());
//
//        updateCourseRating(feedback.getCourseId());
//
//        return result;
//    }
//
//    @Override
//    public int deleteFeedback(int courseId, int userId) {
//        String sql = """
//                DELETE FROM feedback WHERE course_id = ? AND user_id = ?
//                """;
//        int result = jdbcTemplate.update(sql, courseId, userId);
//
//        updateCourseRating(courseId);
//
//        return result;
//    }
//
//    private int updateCourseRating(int courseId){
//        String getAvgSql = """
//                 SELECT AVG(course_rating) FROM feedback WHERE course_id = ?
//                """;
//
//        List<Map<String, Object>> avg = jdbcTemplate.queryForList(getAvgSql, courseId);
//
//        BigDecimal rating = (BigDecimal) avg.get(0).get("avg");
//        DecimalFormat numberFormat = new DecimalFormat("#.0");
//        String ratingTrim = numberFormat.format(rating);
//
//
//        String updateRatingSql = """
//                UPDATE courses SET rating = ? WHERE id = ?
//                """;
//
//        int result = jdbcTemplate.update(updateRatingSql, Double.parseDouble(ratingTrim), courseId);
//
//        return result;
//    }
//
//    private RowMapper<Feedback> getFeedbackRowMapper(){
//        return (resultSet, i) -> {
//            return new Feedback(
//                    resultSet.getInt("id"),
//                    resultSet.getInt("course_id"),
//                    resultSet.getInt("user_id"),
//                    resultSet.getDouble("course_rating"),
//                    resultSet.getString("comment")
//            );
//    };
//    }
//
//}