package gre.jb.service.jobCategoryService;

import gre.jb.entity.JobCategory;
import gre.jb.repository.IJobCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class JobCategoryService implements IJobCatogryService {
    @Autowired
    private IJobCategoryRepo jobCategoryRepo;
    @Override
    public List findAll() {
        return jobCategoryRepo.findAll();
    }



    @Override
    public boolean save(JobCategory jobCategory) {
        jobCategoryRepo.save(jobCategory);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public JobCategory findById(Long id) {
        return jobCategoryRepo.findById(id).get();
    }
}
