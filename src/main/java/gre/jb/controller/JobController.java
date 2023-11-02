package gre.jb.controller;

import gre.jb.entity.Account;
import gre.jb.entity.AccountPrinciple;
import gre.jb.entity.Job;
import gre.jb.entity.JobCategory;
import gre.jb.service.jobCategoryService.IJobCatogryService;
import gre.jb.service.jobService.IJobservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("job")
public class JobController {
    @Autowired
    IJobservice jobservice;
    @Autowired
    IJobCatogryService jobCatogryService;
    @GetMapping("list")
    public ResponseEntity<List<Job>> findAllJob(){
        List<Job> jobs = jobservice.findAll();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }
    @GetMapping("listCategories")
    public ResponseEntity<List<JobCategory>> findAllJobCategories(){
        List<JobCategory> jobs = jobCatogryService.findAll();
        System.out.println(jobs);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Boolean> create(@RequestBody Job job){
        return new ResponseEntity<>(jobservice.save(job),HttpStatus.CREATED);
    }
}
