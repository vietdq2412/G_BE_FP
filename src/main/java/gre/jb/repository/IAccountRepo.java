package gre.jb.repository;

import gre.jb.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepo extends JpaRepository<Account, Long> {
    Account findAccountByEmailAndPassword(String email, String password);
    Account findAccountByUsername(String username);
    boolean existsAccountByUsername(String username);
    boolean existsByEmail(String email);
}
