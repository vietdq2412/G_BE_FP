package gre.jb.controller;

import gre.jb.entity.CV;
import gre.jb.service.CvSevice.CvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
}