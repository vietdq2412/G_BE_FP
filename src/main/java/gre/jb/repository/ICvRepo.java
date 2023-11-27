package gre.jb.repository;

import gre.jb.entity.CV;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICvRepo extends JpaRepository<CV, Long> {
}
