package springboot.app.autoservice.service.impl;

import org.springframework.stereotype.Service;
import springboot.app.autoservice.dao.CarDao;
import springboot.app.autoservice.model.Car;
import springboot.app.autoservice.service.CarService;

import java.util.Optional;

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
