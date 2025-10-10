package in.com.redR.JobApplication.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company com=companyService.getCompanyById(id);
        if(com!=null)
            return ResponseEntity.ok(com);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<String> updateCompanyById(@RequestBody Company updatedCom , @PathVariable Long id){
        boolean ans=companyService.updateCompanyById(id,updatedCom);
        if(ans) return new ResponseEntity<>("Company "+id+" updated", HttpStatus.ACCEPTED);
        return new ResponseEntity<>("Company with id "+id+" not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        boolean ans=companyService.deleteCompany(id);
        if(ans) return new ResponseEntity<>("deleted id "+id, HttpStatus.ACCEPTED);
        return new ResponseEntity<>(" "+id+" not found", HttpStatus.NOT_FOUND);
    }
}
