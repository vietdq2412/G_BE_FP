package gre.jb.repository;

import gre.jb.entity.JobType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IJobTypeRepo extends JpaRepository<JobType, Long> {
}
