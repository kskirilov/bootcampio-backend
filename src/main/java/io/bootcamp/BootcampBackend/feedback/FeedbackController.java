//package io.bootcamp.BootcampBackend.feedback;
//
//import io.bootcamp.BootcampBackend.util.Response;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("api/feedback")
//public class FeedbackController {
//    FeedbackService feedbackService;
//
//    public FeedbackController(FeedbackService feedbackService){
//        this.feedbackService = feedbackService;
//    }
//
//    @GetMapping
//    public List<Feedback> selectAllFeedback(){
//        return feedbackService.selectAllFeedback();
//    }
//
//    @GetMapping("filter")
//    public List<Feedback> selectAllFeedbackForCourse(@RequestParam(defaultValue = "0", required = false) int courseID,
//                                                     @RequestParam(defaultValue = "0", required = false) int userID){
//        return feedbackService.selectAllFeedbackBy(courseID, userID);
//    }
//
//    @DeleteMapping
//    public Response deleteFeedback(@RequestParam int courseID, @RequestParam int userID){
//        return feedbackService.deleteFeedback(courseID, userID);
//    }
//
//    @PostMapping
//    public Response addFeedback(@RequestBody Feedback feedback){
//        return feedbackService.insertFeedback(feedback);
//    }
//
//    @PutMapping
//    public Response updateFeedback(@RequestBody Feedback feedback){
//        return feedbackService.updateFeedback(feedback);
//    }
//
//}
