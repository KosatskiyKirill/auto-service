package autoservice.app.service;

import autoservice.app.model.Service;
import java.math.BigDecimal;
import java.util.Optional;

public interface ServiceService {
    BigDecimal getSalaryForMaster(Long id);

    Service create(Service service);

    Service update(Service service);

    Optional<Service> findById(Long id);
}
