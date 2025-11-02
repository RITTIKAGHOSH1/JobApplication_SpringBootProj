package in.com.redR.JobApplication.review.impl;

import in.com.redR.JobApplication.ExceptionHandler.CompanyIdNotFoundException;
import in.com.redR.JobApplication.ExceptionHandler.NoCompanyFoundException;
import in.com.redR.JobApplication.ExceptionHandler.ReviewIdNotFoundException;
import in.com.redR.JobApplication.company.Company;
import in.com.redR.JobApplication.company.CompanyService;
import in.com.redR.JobApplication.review.Review;
import in.com.redR.JobApplication.review.ReviewRepository;
import in.com.redR.JobApplication.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService){
        this.reviewRepository=reviewRepository;
        this.companyService=companyService;
    }

    @Override
    public boolean addReview(Review review, Long companyId) {
        Company company=companyService.getCompanyById(companyId);
        if(company!=null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        throw new CompanyIdNotFoundException("Company Id- " + companyId + " not found",companyId);
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        List<Review> reviews= reviewRepository.findByCompanyId(companyId);
        for(Review review:reviews){
            if(review.getId().equals(reviewId)){
                reviewRepository.delete(review);
                return true;
            }
        }
        return false;
    }

   @Override
    public boolean updateReviewByIds(Long companyId, Long reviewId, Review updatedReview) {
        List<Review> reviews= reviewRepository.findByCompanyId(companyId);
        if(reviews.isEmpty())
            throw new CompanyIdNotFoundException("Company Id- " + companyId + " not found",companyId);
       for(Review rev:reviews){
            if(rev.getId().equals(reviewId)){
                rev.setCompany(updatedReview.getCompany());
                rev.setRating(updatedReview.getRating());
                rev.setDescription(updatedReview.getDescription());
                rev.setTitle(updatedReview.getTitle());
                reviewRepository.save(rev);
                return true;
            }
        }
        return false;
    }

    //get AlL Reviews of 1 company
    @Override
    public List<Review> getAlLReview(Long companyId) {
      List<Review> reviews= reviewRepository.findByCompanyId(companyId);
      if(!reviews.isEmpty())
          return reviews;
      throw new NoCompanyFoundException("No Company Present in database");
    }

    @Override
    public Review getReviewByReviewId(Long companyId, Long reviewId) {
        List<Review> reviews= reviewRepository.findByCompanyId(companyId);
        for(Review review:reviews){
            if(review.getId().equals(reviewId)){
                return review;
            }
            throw new ReviewIdNotFoundException("Given Review Id not found");
        }
        return null;
    }
}
