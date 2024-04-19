package gre.jb.service.jobService;

import gre.jb.entity.Job;
import gre.jb.entity.JobCategory;
import gre.jb.entity.JobType;
import gre.jb.entity.Location;
import gre.jb.service.IGeneralService;

import java.util.List;

public interface IJobService extends IGeneralService<Job> {
    List<Job> findJobsByCompany_Id(long jobId);
    List<Job> findJobsByNameContaining(String jobName);

    List<Job> findJobsByFilters(JobCategory category, JobType jobType, Location location, String name);

    List<Job> findAllAvailableJobs();
}
