package gre.jb.service.accountService;


import gre.jb.entity.Account;
import gre.jb.entity.AppUser;
import gre.jb.entity.Company;
import gre.jb.entity.Role;
import gre.jb.repository.IAccountRepo;
import gre.jb.service.appUserService.IAppUserService;
import gre.jb.service.companyService.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class AccountService implements IAccountService {

    @Autowired
    IAccountRepo accountRepo;
    @Autowired
    IAppUserService appUserService;
    @Autowired
    ICompanyService companyService;

    @Override
    public List<Account> findAll() {
        return accountRepo.findAll();
    }

    /**
     * @param account include username, email, password
     * @return True if username & email are not existed
     */
    @Override
    public boolean save(Account account) {
        boolean check = accountRepo.existsByEmail(account.getEmail());
        if (!check) {
            accountRepo.save(account);
            account = findByUserName(account.getUsername());
            Role role = account.getRoles().stream().findFirst().get();
            long companyRoleId = 3;
            long userRoleId = 2;
            String notVerified = "0";
            if (role.getId() == userRoleId) {
                AppUser newUser = AppUser.builder()
                        .account(account)
                        .email(account.getEmail())
                        .status(notVerified)
                        .build();
                newUser.setEmail(account.getEmail());
                appUserService.save(newUser);
            } else if (role.getId() == companyRoleId) {
                Company company = Company.builder()
                        .status(notVerified)
                        .email(account.getEmail())
                        .account(account)
                        .build();
                companyService.save(company);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (accountRepo.existsById(id)) {
            accountRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Account findById(Long id) {
        Optional<Account> account = accountRepo.findById(id);
        return account.get();
    }

    public Account findByUserName(String username) {
        return accountRepo.findAccountByUsername(username);
    }

    /**
     * @param account include username and password
     * @return account if existed, null if not found this account
     */
    public Account checkLogin(Account account) {
        Account accountRs = accountRepo.findAccountByEmailAndPassword(account.getEmail(), account.getPassword());
        return accountRs;
    }

    public List<GrantedAuthority> getAuthorities(Long accountID) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        Set<Role> roleList = findById(accountID).getRoles();
        for (Role role : roleList) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
}
