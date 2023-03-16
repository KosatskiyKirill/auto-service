package autoservice.app.service.impl;

import autoservice.app.dao.CarDao;
import autoservice.app.model.Car;
import autoservice.app.service.CarService;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    private final CarDao carDao;

    public CarServiceImpl(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    public Car create(Car car) {
        return carDao.save(car);
    }

    @Override
    public Car update(Car car) {
        return carDao.save(car);
    }

    @Override
    public Optional<Car> findById(Long id) {
        return carDao.findById(id);
    }
}
