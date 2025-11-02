package in.com.redR.JobApplication.company;

import in.com.redR.JobApplication.ExceptionHandler.CompanyIdNotFoundException;
import in.com.redR.JobApplication.ExceptionHandler.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    CompanyService companyService;
    public CompanyController(CompanyService companyService){
        this.companyService=companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getCompanies(){
        return ResponseEntity.ok( companyService.getAllCompany());
    }
    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody Company newCompany){
        companyService.addCompany(newCompany);
       return ResponseEntity.ok("New Company added successfully!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompanyById(@PathVariable Long id) {
        Company com=companyService.getCompanyById(id);
        if(com!=null)
            return ResponseEntity.ok(com);
        try {
            throw new CompanyIdNotFoundException("Company Id- " + id + " not found",id);
        }catch(CompanyIdNotFoundException e){
            ExceptionResponse resp=new ExceptionResponse(LocalDateTime.now(),e.getMessage(),"Given Id- "+ id+ "not found");
            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public  ResponseEntity<String> updateCompanyById(@RequestBody Company updatedCom , @PathVariable Long id){
        boolean ans=companyService.updateCompanyById(id,updatedCom);
        if(ans) return new ResponseEntity<>("Company "+id+" updated", HttpStatus.ACCEPTED);
        throw new CompanyIdNotFoundException("Company Id- " + id + " not found",id);
         }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        boolean ans=companyService.deleteCompany(id);
        if(ans) return new ResponseEntity<>("deleted id "+id, HttpStatus.ACCEPTED);
        throw new CompanyIdNotFoundException("Company Id- " + id + " not found",id);
    }
}
