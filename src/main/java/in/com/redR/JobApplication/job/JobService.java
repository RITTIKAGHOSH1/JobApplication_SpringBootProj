package in.com.redR.JobApplication.job;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobService {
   public abstract List<Job> findAll();
    void createJob(Job job);
    void createJobByCompanyId(Job job, Long companyId);

    Job getJobById(Long id);

    boolean deleteJobById(Long id);
    boolean updateJob(Long id,Job job);
    List<Job> getAllJobsWithPagination(int page, int size);
    List<Job> getJobByIdGreaterThanEqual(Long id);
    List<Job> getJobOrderByLocationDesc();

 Page<Job> getAllJobs(Pageable pageable);
}
