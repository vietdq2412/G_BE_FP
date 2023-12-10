package gre.jb.repository;

import gre.jb.entity.AppUser;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppUserRepo extends JpaRepository<AppUser, Long> {
    AppUser findAppUserByAccount_Id(Long accountId);

    AppUser existsAppUserByAccount_Id(Long accountId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM app_user_skills WHERE app_user_id = ?1 AND skills_id = ?2", nativeQuery = true)
    void deleteSkill(long appUserId, long skillId);
}
