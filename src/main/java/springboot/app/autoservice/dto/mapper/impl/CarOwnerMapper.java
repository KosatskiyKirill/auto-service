package springboot.app.autoservice.dto.mapper.impl;

import org.springframework.stereotype.Component;
import springboot.app.autoservice.dto.CarOwnerDto;
import springboot.app.autoservice.dto.mapper.DtoMapper;
import springboot.app.autoservice.model.CarOwner;

import static java.util.stream.Collectors.toList;

@Component
public class CarOwnerMapper implements DtoMapper<CarOwnerDto, CarOwner> {
    private final CarMapper carMapper;
    private final OrderMapper orderMapper;

    public CarOwnerMapper(CarMapper carMapper, OrderMapper orderMapper) {
        this.carMapper = carMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public CarOwnerDto toDto(CarOwner carOwner) {
        CarOwnerDto dto = new CarOwnerDto();
        dto.setId(carOwner.getId());
        dto.setCars(carOwner.getCars().stream()
                .map(carMapper::toDto)
                .collect(toList()));
        dto.setOrders(carOwner.getOrders().stream()
                .map(orderMapper::toDto)
                .collect(toList()));
        return dto;
    }

    @Override
    public CarOwner toModel(CarOwnerDto dto) {
        CarOwner carOwner = new CarOwner();
        carOwner.setOrders(dto.getOrders().stream()
                .map(orderMapper::toModel)
                .collect(toList()));
        carOwner.setCars(dto.getCars().stream()
                .map(carMapper::toModel)
                .collect(toList()));
        return carOwner;
    }
}
