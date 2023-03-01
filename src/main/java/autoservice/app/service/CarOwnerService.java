package autoservice.app.service;

import autoservice.app.model.CarOwner;
import java.util.Optional;

public interface CarOwnerService {
    CarOwner create(CarOwner carOwner);

    CarOwner update(CarOwner carOwner);

    Optional<CarOwner> findById(Long id);
}
