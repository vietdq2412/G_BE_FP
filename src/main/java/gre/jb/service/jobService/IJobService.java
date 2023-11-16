package gre.jb.service.jobService;

import gre.jb.entity.Job;
import gre.jb.service.IGeneralService;

import java.util.List;

public interface IJobService extends IGeneralService<Job> {
    List<Job> findJobsByCompany_Id(long jobId);
    List<Job> findJobsByNameContaining(String jobName);
}
