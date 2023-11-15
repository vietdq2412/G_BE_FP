package gre.jb.service.companyService;

import gre.jb.entity.Company;
import gre.jb.repository.ICompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService implements ICompanyService {
    @Autowired
    ICompanyRepo companyRepo;

    @Override
    public List<Company> findAll() {
        return companyRepo.findAll();
    }

    @Override
    public boolean save(Company company) {
        return companyRepo.save(company) != null;
    }

    @Override
    public boolean delete(Long id) {
        if (companyRepo.existsById(id)) {
            companyRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Company findById(Long id) {
        Optional<Company> companyOptional = companyRepo.findById(id);
        return companyOptional.orElse(null);
    }

    @Override
    public Company findByAccount(Long id) {
        return companyRepo.findCompanyByAccount_Id(id);
    }

    @Override
    public List<Company> findByDisplayNameContaining(String displayName) {
        List<Company> userList = companyRepo.findCompaniesByNameContaining(displayName);
        return userList;
    }


    @Override
    public void followUser(Long currentUserId, Long userID) {
//OnionGPT

    }
}
