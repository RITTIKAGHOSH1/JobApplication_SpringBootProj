package in.com.redR.JobApplication.job.impl;

import in.com.redR.JobApplication.company.Company;
import in.com.redR.JobApplication.company.CompanyService;
import in.com.redR.JobApplication.job.Job;
import in.com.redR.JobApplication.job.JobRepository;
import in.com.redR.JobApplication.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
   JobRepository jobRepository;
   CompanyService companyService;
   public JobServiceImpl(JobRepository jobRepository, CompanyService companyService){
       this.companyService=companyService;
       this.jobRepository=jobRepository;
   }
    @Override
    public List<Job> findAll() {
       return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public void createJobByCompanyId(Job job, Long companyId) {
       Company com= companyService.getCompanyById(companyId);
       if(com!=null){
           job.setCompany(com);
           jobRepository.save(job);
       }
    }

    @Override
    public Job getJobById(Long id) {
       return jobRepository.findById(id).orElse(null);
   }
    @Override
    public boolean deleteJobById(Long id){
        if(jobRepository.existsById(id)){
            jobRepository.deleteById(id) ;
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
            if(jobOptional.isPresent()){
                Job job=jobOptional.get();
                job.setDescription(updatedJob.getDescription());
                job.setLocation(updatedJob.getLocation());
                job.setTitle(updatedJob.getTitle());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setMinSalary(updatedJob.getMinSalary());
                jobRepository.save(job);
                return true;
            }
        return false;
        }
}
