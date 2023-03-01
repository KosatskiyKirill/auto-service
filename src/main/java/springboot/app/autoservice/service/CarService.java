package springboot.app.autoservice.service;

import springboot.app.autoservice.model.Car;

import java.util.Optional;

public interface CarService {
    Car create(Car car);

    Car update(Car car);

    Optional<Car> findById(Long id);
}
