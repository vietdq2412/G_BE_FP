package gre.jb.service.CvSevice;

import gre.jb.entity.CV;
import gre.jb.entity.EmailDTO;
import gre.jb.entity.Job;
import gre.jb.repository.ICvRepo;
import gre.jb.repository.IJobRepo;
import gre.jb.service.firebaseService.NotificationService;
import gre.jb.service.gmailService.GmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CvService implements ICvService {
    @Autowired
    private ICvRepo iCvRepo;
    @Autowired
    private IJobRepo jobRepo;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private GmailService gmailService;

    @Override
    public List findAll() {
        return iCvRepo.findAll();
    }


    @Override
    public boolean save(CV cv) {
        long jobId = cv.getJob().getId();
        Job job = jobRepo.findById(jobId).get();

        EmailDTO emailDTO = EmailDTO.builder()
                .from(cv.getAppUser().getName())
                .to(job.getCompany().getEmail())
                .jobId(jobId)
                .build();

        gmailService.emailSubmitCV(emailDTO);
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

    @Override
    public boolean isExisted(Long id) {
        return iCvRepo.existsById(id);
    }

    @Override
    public CV acceptCv(long id) {
        CV cv = iCvRepo.findById(id).get();
        cv.setStatus("accepted");
        return cv;
    }

    @Override
    public CV rejectCv(long id) {
        CV cv = iCvRepo.findById(id).get();
        cv.setStatus("rejected");
        return cv;
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
