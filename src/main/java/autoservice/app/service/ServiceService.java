package autoservice.app.service;

import autoservice.app.model.Services;

import java.util.Optional;

public interface ServiceService {
    Double getSalaryForMaster(Long id);

    Services create(Services services);

    Services update(Services services);

    Optional<Services> findById(Long id);
}
