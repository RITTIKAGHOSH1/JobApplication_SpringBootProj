package in.com.redR.JobApplication.review;

import in.com.redR.JobApplication.ExceptionHandler.CompanyIdNotFoundException;
import in.com.redR.JobApplication.ExceptionHandler.JobIdNotFoundException;
import in.com.redR.JobApplication.ExceptionHandler.ReviewIdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/reviews")
public class ReviewController {
    ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    //get all reviews of a particular company
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        return  new ResponseEntity<>(reviewService.getAlLReview(companyId),
                HttpStatus.OK);
    }
    //get a particular review of a company
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId){
        Review review=reviewService.getReviewByReviewId(companyId,reviewId);
        if(review!=null) return  new ResponseEntity<>(review, HttpStatus.OK);
        throw new CompanyIdNotFoundException("Company Id- " + companyId + " not found",companyId);
    }
    @PostMapping
    public ResponseEntity<String> addReview(@RequestBody Review review, @PathVariable Long companyId){
    boolean isReviewAdded= reviewService.addReview(review,companyId);
       if(isReviewAdded) return new ResponseEntity<>("Review Added",HttpStatus.OK);
       return new ResponseEntity<>("Review not added",HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@RequestBody Review review,
                                               @PathVariable Long companyId, @PathVariable Long reviewId){
        boolean isReviewUpdated= reviewService.updateReviewByIds(companyId,reviewId,review);
        if(isReviewUpdated){
            return new ResponseEntity<>("Review updated",HttpStatus.OK);
        }
        throw new ReviewIdNotFoundException("Given Review Id not found");
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        boolean isReviewdeleted= reviewService.deleteReview(companyId,reviewId);
        if(isReviewdeleted){
            return new ResponseEntity<>("Review deleted",HttpStatus.OK);
        }
        throw new ReviewIdNotFoundException("Given Review Id not found");
    }

}
