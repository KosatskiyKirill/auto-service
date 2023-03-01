package springboot.app.autoservice.service.impl;

import org.springframework.stereotype.Service;
import springboot.app.autoservice.dao.MasterDao;
import springboot.app.autoservice.model.Master;
import springboot.app.autoservice.service.MasterService;

import java.util.Optional;

@Service
public class MasterServiceImpl implements MasterService {
    private final MasterDao masterDao;

    public MasterServiceImpl(MasterDao masterDao) {
        this.masterDao = masterDao;
    }

    @Override
    public Master create(Master master) {
        return masterDao.save(master);
    }

    @Override
    public Master update(Master master) {
        return masterDao.save(master);
    }

    @Override
    public Optional<Master> findById(Long id) {
        return masterDao.findById(id);
    }
}
