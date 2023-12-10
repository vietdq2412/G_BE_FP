package gre.jb.service.jobTypeService;

import gre.jb.entity.JobType;
import gre.jb.repository.IJobTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class JobTypeService implements IJobTypeService {
    @Autowired
    IJobTypeRepo jobTypeRepo;

    @Override
    public List<JobType> findAll() {
        return jobTypeRepo.findAll();
    }

    @Override
    public boolean save(JobType jobType) {
        jobTypeRepo.save(jobType);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        if (jobTypeRepo.existsById(id)) {
            jobTypeRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public JobType findById(Long id) {
        return jobTypeRepo.findById(id).orElse(null);
    }
}
