package in.com.redR.JobApplication.job;

import in.com.redR.JobApplication.ExceptionHandler.ExceptionResponse;
import in.com.redR.JobApplication.ExceptionHandler.JobIdNotFoundException;
import in.com.redR.JobApplication.ExceptionHandler.NoJobFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService){
        this.jobService=jobService;
    }

   @GetMapping
    public ResponseEntity<List<Job>>  findAll(){
        return ResponseEntity.ok(jobService.findAll());}

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job=jobService.getJobById(id);
        if(job!=null)
            return new ResponseEntity<>(job, HttpStatus.OK);
        throw new JobIdNotFoundException("Given Job Id not found");
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job added !!",HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean job=jobService.deleteJobById(id);
        if(job){
            return new ResponseEntity<>("Job " +id+" deleted",HttpStatus.ACCEPTED);
        }
        throw new JobIdNotFoundException("Given Job Id not found");
    }

   // @PutMapping("/{id}")
    @RequestMapping(value = "/{id}" , method = RequestMethod.PUT)
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob){
        boolean updated=jobService.updateJob(id,updatedJob);
        if(updated) return ResponseEntity.ok("job "+id+" updated");
        throw new JobIdNotFoundException("Given Job Id not found");
    }

    @GetMapping("/pagination")
    public ResponseEntity<List<Job>> getJobsWithPagination(@RequestParam int page,
                                                           @RequestParam int size){
        List<Job> list= jobService.getAllJobsWithPagination(page, size);
        if(list.isEmpty()) throw new NoJobFoundException("No Job present in database");
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/range")
    public ResponseEntity<List<Job>> getJobsByIdGreaterThanEqual(@RequestParam Long id){
        List<Job> list=jobService.getJobByIdGreaterThanEqual(id);
        if(list.isEmpty())  throw new JobIdNotFoundException("Given Job Id not found");
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/order")
    public ResponseEntity<List<Job>> getJobsInOrderLocationWise(){
        List<Job> list=jobService.getJobOrderByLocationDesc();
        if(list.isEmpty())  throw new NoJobFoundException("No Job found");
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/page-and-sort")
    public ResponseEntity<Page<Job>> getAllJobs(Pageable pageable) {
        Page<Job> jobs = jobService.getAllJobs(pageable);
        return ResponseEntity.ok(jobs);
    }

}
