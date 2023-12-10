package gre.jb.repository;

import gre.jb.entity.Job;
import gre.jb.entity.JobCategory;
import gre.jb.entity.JobType;
import gre.jb.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IJobRepo extends JpaRepository<Job, Long> {
    List<Job> findJobsByCompany_Id(long companyId);
    List<Job> findJobsByNameContaining(String name);

    @Query("SELECT j FROM Job j " +
            "JOIN j.jobCategory jc " +
            "WHERE (:name IS NULL OR LOWER(j.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:category IS NULL OR jc = :category) " +
            "AND (:jobType IS NULL OR j.jobType = :jobType) " +
            "AND (:location IS NULL OR j.location = :location)")
    List<Job> findByCategoryJobTypeLocationAndName(@Param("category") JobCategory category,
                                                   @Param("jobType") JobType jobType,
                                                   @Param("location") Location location,
                                                   @Param("name") String name);
}
