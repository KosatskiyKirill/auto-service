package autoservice.app.service;

import autoservice.app.model.Car;
import java.util.Optional;

public interface CarService {
    Car create(Car car);

    Car update(Car car);

    Optional<Car> findById(Long id);
}
