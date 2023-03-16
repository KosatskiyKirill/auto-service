package autoservice.app.controller;

import autoservice.app.dto.request.CarRequestDto;
import autoservice.app.dto.response.CarResponseDto;
import autoservice.app.model.Car;
import autoservice.app.service.CarOwnerService;
import autoservice.app.service.CarService;
import autoservice.app.service.mapper.CarMapper;
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
    private final CarOwnerService carOwnerService;

    public CarController(CarService carService, CarMapper carMapper,
                         CarOwnerService carOwnerService) {
        this.carService = carService;
        this.carMapper = carMapper;
        this.carOwnerService = carOwnerService;
    }

    @PostMapping
    public ResponseEntity<CarResponseDto> createCar(@RequestBody CarRequestDto carDto) {
        Car newCar = carService.create(carMapper.toModel(carDto));
        return ResponseEntity.ok(carMapper.toDto(newCar));
    }

    @PutMapping("/{carId}")
    public ResponseEntity<CarResponseDto> updateCar(@PathVariable Long carId,
                                                    @RequestBody CarRequestDto carDto) {
        return carService.findById(carId)
                .map(c -> {
                    carOwnerService.findById(carDto.getCarOwnerId())
                                    .ifPresent(c::setCarOwner);
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
