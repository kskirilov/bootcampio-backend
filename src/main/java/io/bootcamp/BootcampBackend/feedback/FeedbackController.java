package io.bootcamp.BootcampBackend.feedback;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/feedback")
public class FeedbackController {
    FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService){
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public List<FeedbackDTO> selectAllFeedback(@RequestParam(defaultValue = "id") String sort){
        return feedbackService.selectAllFeedback(sort);
    }

    @GetMapping("filter")
    public List<FeedbackDTO> selectAllFeedbackForCourse(@RequestParam(defaultValue = "0", required = false) int courseID,
                                                     @RequestParam(defaultValue = "0", required = false) int userID){
        return feedbackService.selectAllFeedbackBy(courseID, userID);
    }

    @DeleteMapping
    public void deleteFeedback(@RequestParam int courseID, @RequestParam int userID){
        feedbackService.deleteFeedback(courseID, userID);
    }

    @PostMapping
    public void addFeedback(@RequestBody FeedbackDTO feedbackDTO){
        feedbackService.insertFeedback(feedbackDTO);
    }

    @PutMapping
    public void updateFeedback(@RequestBody FeedbackDTO feedbackDTO){
        feedbackService.updateFeedback(feedbackDTO);
    }

}
