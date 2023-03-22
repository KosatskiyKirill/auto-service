package autoservice.app.service.impl;

import autoservice.app.dao.ServiceDao;
import autoservice.app.model.Service;
import autoservice.app.model.enums.StatusService;
import autoservice.app.service.ServiceService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
    private static final BigDecimal MASTERS_SALARY = new BigDecimal(0.4);
    private final ServiceDao serviceDao;

    public ServiceServiceImpl(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }

    @Override
    public BigDecimal getSalaryForMaster(Long id) {
        List<Service> serviceList = new ArrayList<>();
        BigDecimal reduce = serviceDao.getAllByStatusAndMasterId(StatusService.NOT_PAID.name(), id)
                .stream()
                .peek(s -> {
                    s.setStatus(StatusService.PAID);
                    serviceList.add(s);
                })
                .map(Service::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        serviceDao.saveAll(serviceList);
        return reduce.multiply(MASTERS_SALARY);
    }

    @Override
    public Service create(Service service) {
        return serviceDao.save(service);
    }

    @Override
    public Service update(Service service) {
        return serviceDao.save(service);
    }

    @Override
    public Optional<Service> findById(Long id) {
        return serviceDao.findById(id);
    }
}
