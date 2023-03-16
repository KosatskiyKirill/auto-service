package autoservice.app.service.mapper;

import autoservice.app.dto.request.CarRequestDto;
import autoservice.app.dto.response.CarResponseDto;
import autoservice.app.model.Car;
import autoservice.app.service.CarOwnerService;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {
    private final CarOwnerService carOwnerService;

    public CarMapper(CarOwnerService carOwnerService) {
        this.carOwnerService = carOwnerService;
    }

    public CarResponseDto toDto(Car car) {
        CarResponseDto carDto = new CarResponseDto();
        carDto.setId(car.getId());
        carDto.setBrand(car.getBrand());
        carDto.setCarNumber(car.getCarNumber());
        carDto.setCarOwnerId(car.getCarOwner().getId());
        carDto.setModel(car.getModel());
        return carDto;
    }

    public Car toModel(CarRequestDto carDto) {
        Car car = new Car();
        carOwnerService.findById(carDto.getCarOwnerId())
                        .ifPresent(car::setCarOwner);
        car.setBrand(carDto.getBrand());
        car.setModel(carDto.getModel());
        car.setCarNumber(carDto.getCarNumber());
        return car;
    }
}
