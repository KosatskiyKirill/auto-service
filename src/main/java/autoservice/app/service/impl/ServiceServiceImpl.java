package autoservice.app.service.impl;

import autoservice.app.model.Services;
import autoservice.app.model.enums.StatusService;
import autoservice.app.dao.ServiceDao;
import autoservice.app.service.ServiceService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceServiceImpl implements ServiceService {
    private final ServiceDao serviceDao;

    public ServiceServiceImpl(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }

    @Override
    public Double getSalaryForMaster(Long id) {
        return (serviceDao.getAllByStatusAndMaster_Id(StatusService.NOT_PAID.name(), id).stream()
                .map(s -> {
                    s.setStatus(StatusService.PAID);
                    return serviceDao.save(s);
                })
                .mapToDouble(Services::getCost)
                .sum()) * 0.4;
    }

    @Override
    public Services create(Services services) {
        return serviceDao.save(services);
    }

    @Override
    public Services update(Services services) {
        return serviceDao.save(services);
    }

    @Override
    public Optional<Services> findById(Long id) {
        return serviceDao.findById(id);
    }
}
