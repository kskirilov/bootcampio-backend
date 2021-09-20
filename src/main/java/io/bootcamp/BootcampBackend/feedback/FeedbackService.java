//package io.bootcamp.BootcampBackend.feedback;
//
//import io.bootcamp.BootcampBackend.exception.AlreadyExistsException;
//import io.bootcamp.BootcampBackend.exception.BadRequestException;
//import io.bootcamp.BootcampBackend.exception.NotFoundException;
//import io.bootcamp.BootcampBackend.util.Response;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class FeedbackService {
//    private final FeedbackDAO feedbackDAO;
//
//    public FeedbackService(FeedbackDAO feedbackDAO){
//        this.feedbackDAO = feedbackDAO;
//    }
//
//    public List<Feedback> selectAllFeedback(){
//        return feedbackDAO.selectAllFeedback();
//    }
//
//    public List<Feedback> selectAllFeedbackBy(int courseId, int userId){
//        List<Feedback> result = null;
//        if (courseId != 0 && userId != 0){
//            result = feedbackDAO.selectAllFeedbackByUserAndCourse(courseId, userId);
//        } else if(courseId == 0){
//            result = feedbackDAO.selectAllFeedbackByUser(userId);
//        } else if (userId == 0){
//            result = feedbackDAO.selectAllFeedbackForCourse(courseId);
//        } else {
//            throw new BadRequestException("Missing filter parameter");
//        }
//
//        return result;
//    }
//
//    public Response insertFeedback(Feedback feedback){
//        if (selectAllFeedbackBy(feedback.getCourseId(), feedback.getUserId()).stream()
//                .anyMatch(f -> f.getId() == feedback.getId())){
//            throw new AlreadyExistsException("Feedback already exists, please send put request instead");
//        }
//
//        feedbackDAO.insertFeedback(feedback);
//        return new Response(feedback.getId(), "Feedback submitted");
//    }
//
//    public Response updateFeedback(Feedback feedback){
//        if (!selectAllFeedbackBy(feedback.getCourseId(), feedback.getUserId()).stream()
//                .anyMatch(f -> f.getId() == feedback.getId())){
//            throw new NotFoundException("Course feedback not found");
//        }
//
//        feedbackDAO.updateFeedback(feedback);
//        return new Response(feedback.getId(), "Feedback updated");
//    }
//
//    public Response deleteFeedback(int courseId, int userId){
//        List<Feedback> feedback = selectAllFeedbackBy(courseId, userId);
//        if (feedback.size() == 0){
//            throw new NotFoundException("Feedback not found");
//        }
//
//        feedbackDAO.deleteFeedback(courseId, userId);
//        return new Response(feedback.get(0).getId(), "Feedback deleted");
//    }
//}
