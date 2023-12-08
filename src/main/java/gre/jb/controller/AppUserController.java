package gre.jb.controller;

import gre.jb.entity.AppUser;
import gre.jb.entity.Skill;
import gre.jb.service.appUserService.AppUserService;
import gre.jb.service.appUserService.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/appUser")
public class AppUserController {

    private final IAppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping
    public ResponseEntity<Boolean> createUser(@RequestBody AppUser appUser) {
        return ResponseEntity.ok(appUserService.save(appUser));
    }

    @GetMapping
    public ResponseEntity<List<AppUser>> getAllUsers() {
        List<AppUser> users = appUserService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id) {
        AppUser user = appUserService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}/{DOB}")
    public ResponseEntity<Boolean> updateUser(@PathVariable Long id, @PathVariable String DOB, @RequestBody AppUser appUser) {
        appUser.setId(id);
        appUser.setDOB(LocalDate.parse(DOB));
        return ResponseEntity.ok(appUserService.save(appUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        appUserService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("addSkill/{id}")
    public ResponseEntity<Boolean> updateUserSkill(@PathVariable Long id, @RequestBody AppUser appUser) {
        AppUser userUpdate = appUserService.findById(id);
        userUpdate.setSkills(appUser.getSkills());
        return ResponseEntity.ok(appUserService.save(userUpdate));
    }

}
