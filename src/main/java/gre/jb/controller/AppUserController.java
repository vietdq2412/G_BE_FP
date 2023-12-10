package gre.jb.controller;

import gre.jb.entity.AppUser;
import gre.jb.service.appUserService.AppUserService;
import gre.jb.service.appUserService.IAppUserService;
import gre.jb.service.skillService.ISkillService;
import gre.jb.service.skillService.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/appUser")
public class AppUserController {
    private final IAppUserService appUserService;
    private final ISkillService skillService;

    @Autowired
    public AppUserController(AppUserService appUserService, SkillService skillService) {
        this.appUserService = appUserService;
        this.skillService = skillService;
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

    @PutMapping("addSkill/{appUserId}/{skillId}")
    public ResponseEntity<Boolean> updateUserSkill(@PathVariable Long appUserId,@PathVariable Long skillId) {
        AppUser userUpdate = appUserService.findById(appUserId);
        userUpdate.getSkills().add(skillService.findById(skillId));
        userUpdate.setSkills(userUpdate.getSkills());
        return ResponseEntity.ok(appUserService.save(userUpdate));
    }
    @PutMapping("deleteSkill/{appUserId}/{skillId}")
    public ResponseEntity<Boolean> deleteSkill(@PathVariable Long appUserId,@PathVariable Long skillId) {
        appUserService.deleteSkill(appUserId,skillId);
        return ResponseEntity.ok(true);
    }

}
