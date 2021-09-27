package io.bootcamp.BootcampBackend.feedback;

import io.bootcamp.BootcampBackend.course.CourseRepository;
import io.bootcamp.BootcampBackend.exception.AlreadyExistsException;
import io.bootcamp.BootcampBackend.exception.BadRequestException;
import io.bootcamp.BootcampBackend.exception.NotFoundException;
import io.bootcamp.BootcampBackend.user.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackService implements FeedbackManagement {
    private final FeedbackRepository feedbackRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    public FeedbackService(FeedbackRepository feedbackRepository, CourseRepository courseRepository, UserRepository userRepository){
        this.feedbackRepository = feedbackRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<FeedbackDTO> selectAllFeedback(String input) {
        List<Feedback> feedbackInRepo = feedbackRepository.findAll(Sort.by(input));
        List<FeedbackDTO> feedbacks = new ArrayList<>();

        feedbackInRepo.stream().forEach(
                f -> {
                    FeedbackDTO feedbackDTO = mapEntityToDTO(f);
                    feedbacks.add(feedbackDTO);
                }
        );
        return feedbacks;
    }

    @Override
    public List<FeedbackDTO> selectAllFeedbackBy(int courseId, int userId) {
        List<Feedback> feedbackInRepo = null;
        if (courseId != 0 && userId != 0){
            feedbackInRepo = feedbackRepository.selectAllFeedbackByCourseAndUser(courseId, userId);
        } else if(courseId == 0 && userId !=0 ){
            feedbackInRepo = feedbackRepository.selectAllFeedbackByUser(userId);
        } else if (userId == 0 && courseId != 0){
            feedbackInRepo = feedbackRepository.selectAllFeedbackForCourse(courseId);
        } else {
            throw new BadRequestException("Missing filter parameter");
        }

        List<FeedbackDTO> feedbacks = new ArrayList<>();
        feedbackInRepo.stream().forEach(f -> {
            FeedbackDTO feedbackDTO = mapEntityToDTO(f);
            feedbacks.add(feedbackDTO);
        });

        return feedbacks;
    }

    @Override
    public void insertFeedback(FeedbackDTO feedbackDTO) {
        System.out.println(feedbackDTO);
        if (!feedbackRepository.selectAllFeedbackByCourseAndUser(feedbackDTO.getCourseId(), feedbackDTO.getUserId()).isEmpty()){
            throw new AlreadyExistsException("Feedback already exists");
        } else if(courseRepository.findById(feedbackDTO.getCourseId()).isEmpty() ||
        userRepository.findById(feedbackDTO.getUserId()).isEmpty()){
            throw new NotFoundException("User or course not registered on database");
        }

        Feedback feedback = new Feedback();
        mapDTOToEntity(feedbackDTO, feedback);

        feedbackRepository.save(feedback);
        double rating = feedbackRepository.calculateCourseAverageRating(feedback.getCourseId().getId());
        courseRepository.updateCourseRating(feedback.getCourseId().getId(), rating);
    }

    @Override
    public void updateFeedback(FeedbackDTO feedbackDTO) {
        if (selectAllFeedbackBy(feedbackDTO.getCourseId(), feedbackDTO.getUserId()).isEmpty()){
            throw new NotFoundException("Course feedback not found");
        }

        Feedback feedback = new Feedback();
        mapDTOToEntity(feedbackDTO, feedback);
        feedbackRepository.save(feedback);

        double rating = feedbackRepository.calculateCourseAverageRating(feedback.getCourseId().getId());
        courseRepository.updateCourseRating(feedback.getCourseId().getId(), rating);
    }

    @Override
    public void deleteFeedback(int courseId, int userId) {
        if (selectAllFeedbackBy(courseId, userId).isEmpty()){
            throw new NotFoundException("Course feedback not found");
        }

        List<FeedbackDTO> feedback = selectAllFeedbackBy(courseId, userId);
        feedbackRepository.deleteById(feedback.get(0).getId());

        double rating = feedbackRepository.calculateCourseAverageRating(courseId);
        courseRepository.updateCourseRating(courseId, rating);
    }

    public FeedbackDTO mapEntityToDTO(Feedback feedback){
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setId(feedback.getId());
        feedbackDTO.setCourseId(feedback.getCourseId().getId());
        feedbackDTO.setUserId(feedback.getUserId().getId());
        feedbackDTO.setRating(feedback.getCourseRating());
        feedbackDTO.setComment(feedback.getComment());
        feedbackDTO.setCreatedAt(LocalDateTime.now());
        return feedbackDTO;
    }

    public void mapDTOToEntity(FeedbackDTO feedbackDTO, Feedback feedback){
        feedback.setCourseId(courseRepository.findById(feedbackDTO.getCourseId()).get());
        feedback.setUserId(userRepository.findById(feedbackDTO.getUserId()).get());
        feedback.setCourseRating(feedbackDTO.getRating());
        feedback.setComment(feedbackDTO.getComment());
        feedback.setCreatedAt(LocalDateTime.now());
        System.out.println("DONE");
    }
}