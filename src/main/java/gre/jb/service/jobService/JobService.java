package gre.jb.service.jobService;

import gre.jb.entity.Job;
import gre.jb.entity.JobCategory;
import gre.jb.entity.JobType;
import gre.jb.entity.Location;
import gre.jb.repository.IJobRepo;
import gre.jb.service.CvSevice.ICvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService implements IJobService {
    @Autowired
    IJobRepo jobRepo;
    @Autowired
    ICvService cvService;

    @Override
    public List findAll() {
        return jobRepo.findAll();
    }

    @Override
    public boolean save(Job job) {
        jobRepo.save(job);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        if (jobRepo.existsById(id)) {
            jobRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Job findById(Long id) {
        return jobRepo.findById(id).orElse(null);
    }

    @Override
    public List<Job> findJobsByCompany_Id(long jobId) {
        return jobRepo.findJobsByCompany_Id(jobId);
    }

    @Override
    public List<Job> findJobsByNameContaining(String jobName) {
        return jobRepo.findJobsByNameContaining(jobName);
    }

    @Override
    public List<Job> findJobsByFilters(JobCategory category, JobType jobType, Location location, String name) {
        return jobRepo.findByCategoryJobTypeLocationAndName(category, jobType, location, name);
    }
}
