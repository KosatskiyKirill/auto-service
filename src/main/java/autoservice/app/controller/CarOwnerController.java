package autoservice.app.controller;

import static java.util.stream.Collectors.toList;

import autoservice.app.dto.request.CarOwnerRequestDto;
import autoservice.app.dto.response.CarOwnerResponseDto;
import autoservice.app.dto.response.OrderResponseDto;
import autoservice.app.model.CarOwner;
import autoservice.app.service.CarOwnerService;
import autoservice.app.service.OrderService;
import autoservice.app.service.mapper.CarMapper;
import autoservice.app.service.mapper.CarOwnerMapper;
import autoservice.app.service.mapper.OrderMapper;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car-owners")
public class CarOwnerController {
    private final CarOwnerService carOwnerService;
    private final CarOwnerMapper carOwnerMapper;
    private final CarMapper carMapper;
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public CarOwnerController(CarOwnerService carOwnerService,
                              CarOwnerMapper carOwnerMapper, CarMapper carMapper,
                              OrderService orderService, OrderMapper orderMapper) {
        this.carOwnerService = carOwnerService;
        this.carOwnerMapper = carOwnerMapper;
        this.carMapper = carMapper;
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    public ResponseEntity<CarOwnerResponseDto> createCarOwner(@RequestBody
                                                                  CarOwnerRequestDto carOwnerDto) {
        CarOwner newCarOwner = carOwnerService.create(carOwnerMapper.toModel(carOwnerDto));
        return ResponseEntity.ok(carOwnerMapper.toDto(newCarOwner));
    }

    @PutMapping("/{carOwnerId}")
    public ResponseEntity<CarOwnerResponseDto> updateCarOwner(@PathVariable Long carOwnerId,
                                                      @RequestBody CarOwnerRequestDto carOwnerDto) {
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
    public ResponseEntity<List<OrderResponseDto>> getCarOwnersOrders(@PathVariable
                                                                         Long carOwnerId) {
        List<OrderResponseDto> orders = orderService.getAllByCarOwnerId(carOwnerId).stream()
                .map(orderMapper::toDto).toList();
        return ResponseEntity.ok(orders);
    }
}
