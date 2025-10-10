package in.com.redR.JobApplication.job;

import java.util.List;

public interface JobService {
   public abstract List<Job> findAll();
    void createJob(Job job);
    void createJobByCompanyId(Job job, Long companyId);

    Job getJobById(Long id);

    boolean deleteJobById(Long id);
    boolean updateJob(Long id,Job job);
}
