package autoservice.app.controller;

import autoservice.app.dto.CarDto;
import autoservice.app.model.Car;
import autoservice.app.dto.mapper.impl.CarMapper;
import autoservice.app.dto.mapper.impl.CarOwnerMapper;
import autoservice.app.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;
    private final CarMapper carMapper;
    private final CarOwnerMapper carOwnerMapper;

    public CarController(CarService carService, CarMapper carMapper, CarOwnerMapper carOwnerMapper) {
        this.carService = carService;
        this.carMapper = carMapper;
        this.carOwnerMapper = carOwnerMapper;
    }

    @PostMapping
    public ResponseEntity<CarDto> createCar(@RequestBody CarDto carDto) {
        Car newCar = carService.create(carMapper.toModel(carDto));
        return ResponseEntity.ok(carMapper.toDto(newCar));
    }

    @PutMapping("/{carId}")
    public ResponseEntity<CarDto> updateCar(@PathVariable Long carId, @RequestBody CarDto carDto) {
        return carService.findById(carId)
                .map(c -> {
                    c.setCarOwner(carOwnerMapper.toModel(carDto.getCarOwnerDto()));
                    c.setBrand(carDto.getBrand());
                    c.setCarNumber(carDto.getCarNumber());
                    c.setModel(carDto.getModel());
                    return carService.update(c);
                })
                .map(carMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }
}
