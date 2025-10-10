package in.com.redR.JobApplication.review;

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
        return  new ResponseEntity<>(reviewService.getReviewByReviewId(companyId,reviewId),
                HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> addReview(@RequestBody Review review, @PathVariable Long companyId){
       boolean isReviewAdded= reviewService.addReview(review,companyId);
       if(isReviewAdded){
        return new ResponseEntity<>("Review Added",HttpStatus.OK);
    }
        return new ResponseEntity<>("Review not added",HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@RequestBody Review review,
                                               @PathVariable Long companyId, @PathVariable Long reviewId){
        boolean isReviewUpdated= reviewService.updateReviewByIds(companyId,reviewId,review);
        if(isReviewUpdated){
            return new ResponseEntity<>("Review updated",HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not found",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        boolean isReviewdeleted= reviewService.deleteReview(companyId,reviewId);
        if(isReviewdeleted){
            return new ResponseEntity<>("Review deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not found",HttpStatus.NOT_FOUND);
    }

}
