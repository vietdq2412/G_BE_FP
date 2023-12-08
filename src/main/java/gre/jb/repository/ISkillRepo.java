package gre.jb.repository;

import gre.jb.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISkillRepo extends JpaRepository<Skill, Long> {
}
