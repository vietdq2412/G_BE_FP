package gre.jb.controller;

import gre.jb.entity.CV;
import gre.jb.service.CvSevice.CvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/cv")
public class CVController {

    @Autowired
    private CvService cvService;

    @PostMapping
    public ResponseEntity<Boolean> createCV(@RequestBody CV cv) {
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
        return new ResponseEntity<>(cvService.existsByAppUser_Id(id),HttpStatus.OK);
    }
    @GetMapping("/testNt")
    public ResponseEntity<Void> testNt() {
        cvService.testNt();
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateCV(@PathVariable Long id, @RequestBody CV cvDetails) {
        if (cvService.isExisted(id)){
            return ResponseEntity.ok(cvService.save(cvDetails));
        }else return (ResponseEntity<Boolean>) ResponseEntity.notFound();
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