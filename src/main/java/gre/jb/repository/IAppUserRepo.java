package gre.jb.repository;

import gre.jb.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAppUserRepo extends JpaRepository<AppUser, Long> {
//    void followUser(Long userID);
    AppUser findAppUserByAccount_Id(Long accountId);
    AppUser existsAppUserByAccount_Id(Long accountId);

}
