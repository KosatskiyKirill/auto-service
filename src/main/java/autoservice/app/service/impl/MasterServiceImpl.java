package autoservice.app.service.impl;

import autoservice.app.dao.MasterDao;
import autoservice.app.model.Master;
import autoservice.app.service.MasterService;
import java.util.Optional;
import org.springframework.stereotype.Service;

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
