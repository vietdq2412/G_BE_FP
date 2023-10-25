//package gre.jb.controller;
//
//
//import gre.jb.entity.Account;
//import gre.jb.entity.AccountPrinciple;
//import gre.jb.entity.AppUser;
//import gre.jb.entity.Role;
//import gre.jb.security.JwtService;
//import gre.jb.service.accountService.AccountService;
//import gre.jb.service.appUserService.IAppUserService;
//import gre.jb.service.roleService.RoleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//
//@RestController
//@CrossOrigin("*")
//@RequestMapping("/account")
//public class AccountController {
//    @Autowired
//    private JwtService jwtService;
//    @Autowired
//    private AccountService accountService;
//
//    @Autowired
//    private IAppUserService appUserService;
//    @Autowired
//    private RoleService roleService;
//
//
//    /* ---------------- GET ALL USER ------------------------ */
//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public ResponseEntity<List<Account>> getAllAccounts() {
//        List<Account> list = accountService.findAll();
//        return new ResponseEntity<List<Account>>(list, HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "test", method = RequestMethod.POST)
//    public ResponseEntity<List<Account>> getAdllAccounts() {
//        List<Account> list = accountService.findAll();
//        list.add(Account.builder().username("đ").build());
//        return new ResponseEntity<List<Account>>(list, HttpStatus.OK);
//    }
//
//    /* ---------------- GET USER BY ID ------------------------ */
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public ResponseEntity<Object> getAccountById(@PathVariable Long id) {
//        Account account = accountService.findById(id);
//        if (account != null) {
//            return new ResponseEntity<Object>(account, HttpStatus.OK);
//        }
//        return new ResponseEntity<Object>("Not Found User", HttpStatus.NO_CONTENT);
//    }
//
//    /* ---------------- CREATE NEW USER ------------------------ */
//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public ResponseEntity<String> createAccount(@RequestBody Account account) {
////        Set<Role> setRole = new HashSet<>();
////        setRole.add(roleService.findById(2L));
////        account.setRoles(setRole);
////
////        if (accountService.save(account)) {
////            return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
////        } else {
////            return new ResponseEntity<String>("Username or email Existed!", HttpStatus.BAD_REQUEST);
////        }
//        System.out.println(account.getUsername());
//        System.out.println("hi");
//        return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
//    }
//
//    /* ---------------- DELETE USER ------------------------ */
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<String> deleteAccountById(@PathVariable Long id) {
//        accountService.delete(id);
//        return new ResponseEntity<String>("Deleted!", HttpStatus.OK);
//    }
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ResponseEntity<AccountPrinciple> login(@RequestBody Account account) {
//        String result = "";
//        HttpStatus httpStatus = null;
//        account = accountService.checkLogin(account);
//        AppUser appUser = appUserService.findByAccount(account.getId());
//        AccountPrinciple accountPrinciple = null;
//        try {
//            if (account != null) {
//                result = jwtService.generateTokenLogin(account.getUsername());
//                httpStatus = HttpStatus.OK;
//                accountPrinciple = AccountPrinciple.builder()
//                        .accountId(account.getId())
//                        .userId(appUser.getId())
//                        .username(account.getUsername())
//                        .roles(account.getRoles())
//                        .token(result)
//                        .status(appUser.getStatus())
//                        .build();
//            } else {
//                result = "Wrong userId and password";
//                httpStatus = HttpStatus.BAD_REQUEST;
//            }
//        } catch (Exception ex) {
//            result = "Server Error";
//            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return new ResponseEntity<>(accountPrinciple, httpStatus);
//    }
//
//    @GetMapping("/logout")
//    public ResponseEntity<Integer> logout(int tokenStatus){
//        return new ResponseEntity<>(tokenStatus, HttpStatus.PROXY_AUTHENTICATION_REQUIRED);
//    }
//
//}