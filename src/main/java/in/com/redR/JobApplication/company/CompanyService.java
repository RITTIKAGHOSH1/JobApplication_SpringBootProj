package in.com.redR.JobApplication.company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompany();
    boolean updateCompanyById(Long id , Company updatedCompany);
    void addCompany(Company company);
    boolean deleteCompany(Long id);
    Company getCompanyById(Long id);
}
