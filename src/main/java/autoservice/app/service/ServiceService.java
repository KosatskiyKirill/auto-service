package autoservice.app.service;

import autoservice.app.model.Services;
import java.math.BigDecimal;
import java.util.Optional;

public interface ServiceService {
    BigDecimal getSalaryForMaster(Long id);

    Services create(Services services);

    Services update(Services services);

    Optional<Services> findById(Long id);
}
