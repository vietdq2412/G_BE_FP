package gre.jb.controller;

import gre.jb.entity.Job;
import gre.jb.entity.JobCategory;
import gre.jb.service.jobCategoryService.IJobCatogryService;
import gre.jb.service.jobService.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("jobs")
public class JobController {
    @Autowired
    IJobService jobService;

    @Autowired
    IJobCatogryService jobCatogryService;

    @GetMapping("listJobCategories")
    public ResponseEntity<List<JobCategory>> findAllJobCategories() {
        List<JobCategory> jobs = jobCatogryService.findAll();
        System.out.println(jobs);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> jobs = jobService.findAll();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Job job = jobService.findById(id);
        return job != null ? new ResponseEntity<>(job, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Void> createJob(@RequestBody Job job) {
        job.setDate(new Date());
        jobService.save(job);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
        Job existingJob = jobService.findById(id);
        if (existingJob != null) {
            existingJob.setName(updatedJob.getName());
            existingJob.setJobLevel(updatedJob.getJobLevel());
            existingJob.setRequiredExperience(updatedJob.getRequiredExperience());
            existingJob.setRequiredEducation(updatedJob.getRequiredEducation());
            existingJob.setJobCategory(updatedJob.getJobCategory());
            existingJob.setCompany(updatedJob.getCompany());
            existingJob.setDate(new Date());

            jobService.save(existingJob);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        if (jobService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<Job>> getJobsByCompanyId(@PathVariable Long companyId) {
        List<Job> jobs = jobService.findJobsByCompany_Id(companyId);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("/search/{jobName}")
    public ResponseEntity<List<Job>> searchJobsByName(@PathVariable String jobName) {
        List<Job> jobs = jobService.findJobsByNameContaining(jobName);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }
}
