package gre.jb.controller;


import gre.jb.entity.*;
import gre.jb.security.JwtService;
import gre.jb.service.accountService.AccountService;
import gre.jb.service.appUserService.IAppUserService;
import gre.jb.service.companyService.ICompanyService;
import gre.jb.service.roleService.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private IAppUserService appUserService;
    @Autowired
    private ICompanyService companyService;
    @Autowired
    private IRoleService roleService;

    @GetMapping("/getCompanyProfile/{id}")
    public ResponseEntity<Company> getCompanyProfile(@PathVariable Long id){
        return new ResponseEntity<>(companyService.findById(id), HttpStatus.OK);
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> list = accountService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "test", method = RequestMethod.POST)
    public ResponseEntity<List<Account>> getAdllAccounts() {
        List<Account> list = accountService.findAll();
        list.add(Account.builder().username("đ").build());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getAccountById(@PathVariable Long id) {
        Account account = accountService.findById(id);
        if (account != null) {
            return new ResponseEntity<>(account, HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found User", HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/register/{roleId}")
    public ResponseEntity<Object> createAccount(@RequestBody Account account, @PathVariable String roleId) {
        Set<Role> setRole = new HashSet<>();
        Role role = roleService.findById(Long.valueOf(roleId));
        setRole.add(role);
        account.setRoles(setRole);
        account.setUsername(account.getEmail());

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());

        if (accountService.save(account)) {
            body.put("message", "Success!");
            return new ResponseEntity<>(body, HttpStatus.CREATED);
        } else {
            body.put("message", "Duplicated Email!");
            return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAccountById(@PathVariable Long id) {
        accountService.delete(id);
        return new ResponseEntity<>("Deleted!", HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AccountPrinciple> login(@RequestBody Account account) {
        String result = "";
        HttpStatus httpStatus;
        account = accountService.checkLogin(account);
        AccountPrinciple accountPrinciple = null;

        if (account != null){
            long profileId;
            String status;
            if (account.getRoles().iterator().next().getId() < 3){
                AppUser appUser = appUserService.findByAccount(account.getId());
                profileId = appUser.getId();
                status = appUser.getStatus();
            }else {
                Company company = companyService.findByAccount(account.getId());
                profileId = company.getId();
                status = company.getStatus();
            }

            try {
                result = jwtService.generateTokenLogin(account.getUsername());
                httpStatus = HttpStatus.OK;
                accountPrinciple = AccountPrinciple.builder()
                        .accountId(account.getId())
                        .profileId(profileId)
                        .username(account.getUsername())
                        .roles(account.getRoles())
                        .token(result)
                        .status(status)
                        .build();
            } catch (Exception ex) {
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            return new ResponseEntity<>(accountPrinciple, httpStatus);
        }else {
            httpStatus = HttpStatus.NON_AUTHORITATIVE_INFORMATION;
            return new ResponseEntity<>(null, httpStatus);
        }

    }

    @GetMapping("/logout")
    public ResponseEntity<Integer> logout(int tokenStatus) {

        return new ResponseEntity<>(tokenStatus, HttpStatus.PROXY_AUTHENTICATION_REQUIRED);
    }

}