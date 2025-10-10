package in.com.redR.JobApplication.review;

import java.util.List;

public interface ReviewService {
    boolean addReview(Review review,Long companyId);
    boolean deleteReview(Long companyId,Long reviewId);
    boolean updateReviewByIds(Long companyId, Long reviewId, Review review);
    List<Review> getAlLReview(Long companyId);
    Review getReviewByReviewId(Long companyId, Long reviewId);
}
