package gre.jb.controller;

import gre.jb.entity.AppUser;
import gre.jb.service.appUserService.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/test")
public class HomeController {
    @Autowired
    private AppUserService appUserService;
    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id) {
        AppUser appUser = appUserService.findById(id);
        if (appUser == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(appUser);
    }

    @GetMapping("/home")
    public ResponseEntity<AppUser> t(){
        AppUser appUser = AppUser.builder().displayName("sdsd").build();
        return ResponseEntity.ok(appUser);
    }

}
