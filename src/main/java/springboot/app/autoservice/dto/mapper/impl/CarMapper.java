package springboot.app.autoservice.dto.mapper.impl;

import org.springframework.stereotype.Component;
import springboot.app.autoservice.dto.CarDto;
import springboot.app.autoservice.dto.mapper.DtoMapper;
import springboot.app.autoservice.model.Car;

@Component
public class CarMapper implements DtoMapper<CarDto, Car> {
    private final CarOwnerMapper carOwnerMapper;
    private final OrderMapper orderMapper;

    public CarMapper(CarOwnerMapper carOwnerMapper, OrderMapper orderMapper) {
        this.carOwnerMapper = carOwnerMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public CarDto toDto(Car car) {
        CarDto dto = new CarDto();
        dto.setId(car.getId());
        dto.setBrand(car.getBrand());
        dto.setCarNumber(car.getCarNumber());
        dto.setCarOwnerDto(carOwnerMapper.toDto(car.getCarOwner()));
        dto.setModel(car.getModel());
        return null;
    }

    @Override
    public Car toModel(CarDto dto) {
        Car car = new Car();
        car.setCarOwner(carOwnerMapper.toModel(dto.getCarOwnerDto()));
        car.setBrand(dto.getBrand());
        car.setModel(dto.getModel());
        car.setCarNumber(dto.getCarNumber());
        return car;
    }
}
