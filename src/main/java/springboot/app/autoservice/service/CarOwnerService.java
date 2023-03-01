package springboot.app.autoservice.service;

import springboot.app.autoservice.model.CarOwner;
import java.util.Optional;

public interface CarOwnerService {
    CarOwner create(CarOwner carOwner);

    CarOwner update(CarOwner carOwner);

    Optional<CarOwner> findById(Long id);
}
