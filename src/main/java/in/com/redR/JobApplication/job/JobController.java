package in.com.redR.JobApplication.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job added bro!!",HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean job=jobService.deleteJobById(id);
        if(job){
            return new ResponseEntity<>("Job " +id+" deleted",HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("job "+id+" not found",HttpStatus.NOT_FOUND);
    }
   // @PutMapping("/{id}")
    @RequestMapping(value = "/{id}" , method = RequestMethod.PUT)
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob){
        boolean updated=jobService.updateJob(id,updatedJob);
        if(updated) return ResponseEntity.ok("job "+id+" updated");
        return new ResponseEntity<>("job "+id+" not found",HttpStatus.NOT_FOUND);
    }
}
