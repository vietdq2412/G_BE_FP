package gre.jb.repository;

import gre.jb.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICompanyRepo extends JpaRepository<Company, Long> {
    Company findCompanyByAccount_Id(Long accountId);
    boolean existsCompanyByAccount_Id(Long accountId);
    List<Company> findCompaniesByNameContaining(String displayName);
}
