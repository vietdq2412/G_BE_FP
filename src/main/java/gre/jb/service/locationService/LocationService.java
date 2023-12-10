package gre.jb.service.locationService;

import gre.jb.entity.Location;
import gre.jb.repository.ILocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService implements ILocationService {
    @Autowired
    private ILocationRepo locationRepo;

    @Override
    public List findAll() {
        return locationRepo.findAll();
    }

    @Override
    public boolean save(Location location) {
        locationRepo.save(location);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        locationRepo.deleteById(id);
        return true;
    }

    @Override
    public Location findById(Long id) {
        return locationRepo.findById(id).orElse(null);
    }
}
