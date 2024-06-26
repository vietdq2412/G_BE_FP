package gre.jb.controller;

import gre.jb.entity.CV;
import gre.jb.service.CvSevice.ICvService;
import gre.jb.service.gmailService.GmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/cv")
public class CVController {

    @Autowired
    private ICvService cvService;
    @Autowired
    private GmailService gmailService;

    @PostMapping
    public ResponseEntity<Boolean> createCV(@RequestBody CV cv) {
        cv.setDate(new Date());
        cv.setStatus("pending");
        return ResponseEntity.ok(cvService.save(cv));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CV> getCVById(@PathVariable Long id) {
        CV cv = cvService.findById(id);
        return ResponseEntity.ok(cv);
    }

    @GetMapping("/existsByAppUser/{id}")
    public ResponseEntity<Boolean> existsByAppUserId(@PathVariable Long id) {
        cvService.existsByAppUser_Id(id);
        return new ResponseEntity<>(cvService.existsByAppUser_Id(id), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateCV(@PathVariable Long id, @RequestBody CV cvDetails) {
        if (cvService.isExisted(id)) {
            return ResponseEntity.ok(cvService.save(cvDetails));
        } else return (ResponseEntity<Boolean>) ResponseEntity.notFound();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCV(@PathVariable Long id) {
        cvService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CV>> getAllCVs() {
        List<CV> cvs = cvService.findAll();
        return ResponseEntity.ok(cvs);
    }

    @GetMapping("/accept/{id}")
    public ResponseEntity<CV> acceptCv(@PathVariable Long id) {
        CV cv = cvService.findById(id);
        cv.setStatus("accepted");
        cvService.save(cv);
        return ResponseEntity.ok(cv);
    }

    @GetMapping("/reject/{id}")
    public ResponseEntity<CV> rejectCv(@PathVariable Long id) {
        CV cv = cvService.findById(id);
        cv.setStatus("rejected");
        cvService.save(cv);
        return ResponseEntity.ok(cv);
    }

    @GetMapping("/job/{id}")
    public ResponseEntity<List<CV>> getAllByJobId(@PathVariable Long id) {
        List<CV> cvs = cvService.findCVSByJob_Id(id);
        return ResponseEntity.ok(cvs);
    }

    @GetMapping("/appUser/{id}")
    public ResponseEntity<List<CV>> getAllByAppUserId(@PathVariable Long id) {
        List<CV> cvs = cvService.findAllByAppUser_Id(id);
        return ResponseEntity.ok(cvs);
    }
}