package autoservice.app.controller;

import autoservice.app.dto.CarOwnerDto;
import autoservice.app.dto.OrderDto;
import autoservice.app.dto.mapper.impl.CarMapper;
import autoservice.app.dto.mapper.impl.CarOwnerMapper;
import autoservice.app.dto.mapper.impl.OrderMapper;
import autoservice.app.model.CarOwner;
import autoservice.app.service.CarOwnerService;
import autoservice.app.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/car-owners")
public class CarOwnerController {
    private final CarOwnerService carOwnerService;
    private final CarOwnerMapper carOwnerMapper;
    private final CarMapper carMapper;
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public CarOwnerController(CarOwnerService carOwnerService, CarOwnerMapper carOwnerMapper,
                              CarMapper carMapper, OrderService orderService, OrderMapper orderMapper) {
        this.carOwnerService = carOwnerService;
        this.carOwnerMapper = carOwnerMapper;
        this.carMapper = carMapper;
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    public ResponseEntity<CarOwnerDto> createCarOwner(@RequestBody CarOwnerDto carOwnerDto) {
        CarOwner newCarOwner = carOwnerService.create(carOwnerMapper.toModel(carOwnerDto));
        return ResponseEntity.ok(carOwnerMapper.toDto(newCarOwner));
    }

    @PutMapping("/{carOwnerId}")
    public ResponseEntity<CarOwnerDto> updateCarOwner(@PathVariable Long carOwnerId,
                                                      @RequestBody CarOwnerDto carOwnerDto) {
        return carOwnerService.findById(carOwnerId)
                .map(c -> {
                    c.setCars(carOwnerDto.getCars().stream()
                            .map(carMapper::toModel)
                            .collect(toList()));
                    c.setOrders(carOwnerDto.getOrders().stream()
                            .map(orderMapper::toModel)
                            .collect(toList()));
                    return carOwnerService.update(c);
                })
                .map(carOwnerMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/{carOwnerId}/orders")
    public ResponseEntity<List<OrderDto>> getCarOwnersOrders(@PathVariable Long carOwnerId) {
        return carOwnerService.findById(carOwnerId)
                .map(c -> orderService.getAllByCarOwnerId(carOwnerId).stream()
                        .map(orderMapper::toDto)
                        .collect(toList()))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }
}
