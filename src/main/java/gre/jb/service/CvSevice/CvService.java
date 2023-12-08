package gre.jb.service.CvSevice;

import gre.jb.entity.CV;
import gre.jb.repository.ICvRepo;
import gre.jb.service.firebaseService.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CvService implements ICvService {
    @Autowired
    private ICvRepo iCvRepo;
    @Autowired
    private NotificationService notificationService;

    @Override
    public List findAll() {
        return iCvRepo.findAll();
    }

    public void testNt() {
        System.out.println("test");
        try {
            notificationService.sendNotification("CV submited!", "A candidate has applied a CV to your job!", "CV_Notify");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean save(CV cv) {
        iCvRepo.save(cv);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        iCvRepo.deleteById(id);
        return true;
    }

    @Override
    public CV findById(Long id) {
        return iCvRepo.findById(id).get();
    }

    public boolean isExisted(long id) {
        return iCvRepo.existsById(id);
    }

    @Override
    public boolean existsByAppUser_Id(long appUserId) {
        return iCvRepo.existsByAppUser_Id(appUserId);
    }

    @Override
    public List<CV> findCVSByJob_Id(long id) {
        return iCvRepo.findCVSByJob_Id(id);
    }

    public List<CV> findAllByAppUser_Id(long id) {
        return iCvRepo.findAllByAppUser_Id(id);
    }
}
