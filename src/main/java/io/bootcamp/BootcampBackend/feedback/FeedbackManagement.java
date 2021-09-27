package io.bootcamp.BootcampBackend.feedback;

import java.util.List;

public interface FeedbackManagement {
    List<FeedbackDTO> selectAllFeedback(String input);
    List<FeedbackDTO> selectAllFeedbackBy(int courseId, int userId);
    void insertFeedback(FeedbackDTO feedbackDTO);
    void updateFeedback(FeedbackDTO feedbackDTO);
    void deleteFeedback(int courseId, int userId);
}
