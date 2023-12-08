package gre.jb.repository;

import gre.jb.entity.CV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICvRepo extends JpaRepository<CV, Long> {
    boolean existsByAppUser_Id(long appUserId);
    List<CV> findCVSByJob_Id(long id);
    List<CV> findAllByAppUser_Id(long id);
}
