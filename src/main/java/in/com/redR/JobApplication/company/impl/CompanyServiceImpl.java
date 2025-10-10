package in.com.redR.JobApplication.company.impl;

import in.com.redR.JobApplication.company.Company;
import in.com.redR.JobApplication.company.CompanyRepository;
import in.com.redR.JobApplication.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    CompanyRepository companyRepository;
    public CompanyServiceImpl(CompanyRepository companyRepository){
        this.companyRepository=companyRepository;
    }
    @Override
    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompanyById(Long id,Company updatedCompany) {
        Optional<Company> optionalCompany=companyRepository.findById(id);
        if(optionalCompany.isPresent()){
            Company com=optionalCompany.get();
            com.setJob(updatedCompany.getJob());
            com.setName(updatedCompany.getName());
            com.setDescription(updatedCompany.getDescription());
            companyRepository.save(com);
            return true;
        }
        return false;
    }

    @Override
    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompany(Long id) {
        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }
        return false;

    }

    @Override
    public Company getCompanyById(Long id) {
       Optional<Company> companyOptional =companyRepository.findById(id);
       if(companyOptional.isPresent()) {
           Company com = companyOptional.get();
           return com;
       }
       return null;
    }
}
