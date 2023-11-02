package gre.jb.service.jobService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class JobService implements IJobservice{
    @Override
    public List findAll() {
        return null;
    }

    @Override
    public boolean save(Object o) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Object findById(Long id) {
        return null;
    }
}
