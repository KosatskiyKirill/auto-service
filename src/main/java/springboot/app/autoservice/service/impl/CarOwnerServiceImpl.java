package springboot.app.autoservice.service.impl;

import org.springframework.stereotype.Service;
import springboot.app.autoservice.dao.CarOwnerDao;
import springboot.app.autoservice.model.CarOwner;
import springboot.app.autoservice.service.CarOwnerService;
import java.util.Optional;

@Service
public class CarOwnerServiceImpl implements CarOwnerService {
    private final CarOwnerDao carOwnerRepository;

    public CarOwnerServiceImpl(CarOwnerDao carOwnerRepository) {
        this.carOwnerRepository = carOwnerRepository;
    }

    @Override
    public CarOwner create(CarOwner carOwner) {
        return carOwnerRepository.save(carOwner);
    }

    @Override
    public CarOwner update(CarOwner carOwner) {
        return carOwnerRepository.save(carOwner);
    }

    @Override
    public Optional<CarOwner> findById(Long id) {
        return carOwnerRepository.findById(id);
    }
}
