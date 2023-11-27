package gre.jb.service.CvSevice;

import gre.jb.entity.CV;
import gre.jb.repository.ICvRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CvService implements ICvService {
    @Autowired
    private ICvRepo iCvRepo;
    @Override
    public List findAll() {
        return iCvRepo.findAll();
    }

    @Override
    public boolean save(CV cv) {
        iCvRepo.save(cv);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public CV findById(Long id) {
        return iCvRepo.findById(id).get();
    }

    public boolean isExisted(long id){
        return iCvRepo.existsById(id);
    }
}
