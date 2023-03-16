package autoservice.app.service.impl;

import autoservice.app.dao.CarOwnerDao;
import autoservice.app.model.CarOwner;
import autoservice.app.service.CarOwnerService;
import java.util.Optional;
import org.springframework.stereotype.Service;

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
