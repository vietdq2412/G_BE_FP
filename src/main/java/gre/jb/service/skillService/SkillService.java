package gre.jb.service.skillService;

import gre.jb.entity.Skill;
import gre.jb.repository.ISkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService implements ISkillService {
    @Autowired
    private ISkillRepo skillRepo;

    @Override
    public List findAll() {
        return skillRepo.findAll();
    }

    @Override
    public boolean save(Skill skill) {
        skillRepo.save(skill);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        skillRepo.deleteById(id);
        return true;
    }

    @Override
    public Skill findById(Long id) {
        return skillRepo.findById(id).orElse(null);
    }
}
