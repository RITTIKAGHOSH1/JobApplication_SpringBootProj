package in.com.redR.JobApplication.company;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import in.com.redR.JobApplication.job.Job;
import in.com.redR.JobApplication.review.Review;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    public Company(){
    }
    @OneToMany(mappedBy = "company") //company field in Job class, else compiler will create another table with company and job id only
    @JsonManagedReference(value = "company-job")
    private List<Job> job;

    //each company will have list of reviews
    @OneToMany(mappedBy = "company")
    @JsonManagedReference(value = "company-review")
    private List<Review> reviews;

    public List<Review> getReviews() {
        return reviews;
    }
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Job> getJob() {
        return job;
    }

    public void setJob(List<Job> job) {
        this.job = job;
    }
}
