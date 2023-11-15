package gre.jb.service.companyService;


import gre.jb.entity.AppUser;
import gre.jb.entity.Company;
import gre.jb.service.IGeneralService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICompanyService extends IGeneralService<Company> {
    void followUser(Long currentUserId, Long userID);

    Company findByAccount(Long id);

    List<Company> findByDisplayNameContaining(String displayName);

}
