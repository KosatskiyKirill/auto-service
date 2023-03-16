package autoservice.app.service.mapper;

import static java.util.stream.Collectors.toList;

import autoservice.app.dto.request.CarOwnerRequestDto;
import autoservice.app.dto.response.CarOwnerResponseDto;
import autoservice.app.model.CarOwner;
import org.springframework.stereotype.Component;

@Component
public class CarOwnerMapper {
    private final CarMapper carMapper;
    private final OrderMapper orderMapper;

    public CarOwnerMapper(CarMapper carMapper, OrderMapper orderMapper) {
        this.carMapper = carMapper;
        this.orderMapper = orderMapper;
    }

    public CarOwnerResponseDto toDto(CarOwner carOwner) {
        CarOwnerResponseDto carOwnerDto = new CarOwnerResponseDto();
        carOwnerDto.setId(carOwner.getId());
        carOwnerDto.setCars(carOwner.getCars().stream()
                .map(carMapper::toDto)
                .collect(toList()));
        carOwnerDto.setOrders(carOwner.getOrders().stream()
                .map(orderMapper::toDto)
                .collect(toList()));
        return carOwnerDto;
    }

    public CarOwner toModel(CarOwnerRequestDto carOwnerDto) {
        CarOwner carOwner = new CarOwner();
        carOwner.setOrders(carOwnerDto.getOrders().stream()
                .map(orderMapper::toModel)
                .collect(toList()));
        carOwner.setCars(carOwnerDto.getCars().stream()
                .map(carMapper::toModel)
                .collect(toList()));
        return carOwner;
    }
}
