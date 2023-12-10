package gre.jb.repository;

import gre.jb.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILocationRepo extends JpaRepository<Location, Long> {
}
