package gre.jb.repository;

import gre.jb.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IJobRepo extends JpaRepository<Job, Long> {
    List<Job> findJobsByCompany_Id(long companyId);
    List<Job> findJobsByNameContaining(String name);
}
