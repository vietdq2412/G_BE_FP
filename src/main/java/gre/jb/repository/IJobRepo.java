package gre.jb.repository;

import gre.jb.entity.AppUser;
import gre.jb.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJobRepo extends JpaRepository<Job, Long> {
}
