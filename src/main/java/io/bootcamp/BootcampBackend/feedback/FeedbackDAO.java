package io.bootcamp.BootcampBackend.feedback;

import java.util.List;

public interface FeedbackDAO {
    List<Feedback> selectAllFeedback();
    List<Feedback> selectAllFeedbackForCourse(int courseId);
    List<Feedback> selectAllFeedbackByUser(int userId);
    List<Feedback> selectAllFeedbackByUserAndCourse(int courseId, int userId);
    int insertFeedback(Feedback feedback);
    int updateFeedback(Feedback feedback);
    int deleteFeedback(int courseId, int userId);
}
