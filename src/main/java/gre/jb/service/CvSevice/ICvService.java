package gre.jb.service.CvSevice;

import gre.jb.entity.CV;
import gre.jb.service.IGeneralService;

import java.util.List;

public interface ICvService extends IGeneralService<CV> {
    boolean existsByAppUser_Id(long appUserId);

    List<CV> findCVSByJob_Id(long id);

    List<CV> findAllByAppUser_Id(long id);
}
