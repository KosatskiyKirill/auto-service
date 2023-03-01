package springboot.app.autoservice.service.impl;

import org.springframework.stereotype.Service;
import springboot.app.autoservice.dao.ServiceDao;
import springboot.app.autoservice.model.Services;
import springboot.app.autoservice.model.enums.StatusService;
import springboot.app.autoservice.service.ServiceService;

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
