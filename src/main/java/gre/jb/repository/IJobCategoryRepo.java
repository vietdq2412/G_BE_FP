package gre.jb.repository;

import gre.jb.entity.AppUser;
import gre.jb.entity.JobCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IJobCategoryRepo extends JpaRepository<JobCategory, Long> {
}
