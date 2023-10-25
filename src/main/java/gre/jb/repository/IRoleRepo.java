package gre.jb.repository;

import gre.jb.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepo extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);
}
