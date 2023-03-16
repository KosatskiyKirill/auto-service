package autoservice.app.service.impl;

import autoservice.app.dao.ServiceDao;
import autoservice.app.model.Services;
import autoservice.app.model.enums.StatusService;
import autoservice.app.service.ServiceService;
import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ServiceServiceImpl implements ServiceService {
    private static final BigDecimal MASTERS_SALARY = new BigDecimal(0.4);
    private final ServiceDao serviceDao;

    public ServiceServiceImpl(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }

    @Override
    public BigDecimal getSalaryForMaster(Long id) {
        BigDecimal reduce = serviceDao.getAllByStatusAndMasterId(StatusService.NOT_PAID.name(), id)
                .stream()
                .map(s -> {
                    s.setStatus(StatusService.PAID);
                    return serviceDao.save(s);
                })
                .map(Services::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return reduce.multiply(MASTERS_SALARY);
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
