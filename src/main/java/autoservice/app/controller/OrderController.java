package autoservice.app.controller;

import static java.util.stream.Collectors.toList;

import autoservice.app.dto.request.OrderRequestDto;
import autoservice.app.dto.request.ProductRequestDto;
import autoservice.app.dto.response.OrderResponseDto;
import autoservice.app.model.Order;
import autoservice.app.model.enums.StatusOrder;
import autoservice.app.service.CarOwnerService;
import autoservice.app.service.CarService;
import autoservice.app.service.MasterService;
import autoservice.app.service.OrderService;
import autoservice.app.service.mapper.OrderMapper;
import autoservice.app.service.mapper.ProductMapper;
import autoservice.app.service.mapper.ServiceMapper;
import java.math.BigDecimal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ProductMapper productMapper;
    private final OrderMapper orderMapper;
    private final ServiceMapper serviceMapper;
    private final CarService carService;
    private final MasterService masterService;
    private final CarOwnerService carOwnerService;

    public OrderController(OrderService orderService, ProductMapper productMapper,
                           OrderMapper orderMapper, ServiceMapper serviceMapper,
                           CarService carService, MasterService masterService,
                           CarOwnerService carOwnerService) {
        this.orderService = orderService;
        this.productMapper = productMapper;
        this.orderMapper = orderMapper;
        this.serviceMapper = serviceMapper;
        this.carService = carService;
        this.masterService = masterService;
        this.carOwnerService = carOwnerService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderDto) {
        Order newOrder = orderService.create(orderMapper.toModel(orderDto));
        return ResponseEntity.ok(orderMapper.toDto(newOrder));
    }

    @PostMapping("/add-product-to-order/{orderId}")
    public ResponseEntity<OrderResponseDto> addProductToOrder(@PathVariable Long orderId,
                                                      @RequestBody ProductRequestDto productDto) {
        return orderService.findById(orderId)
                .map(o -> {
                    o.getProducts().add(productMapper.toModel(productDto));
                    return orderService.create(o);
                })
                .map(orderMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> updateOrder(@PathVariable Long orderId,
                                                @RequestBody OrderRequestDto orderDto) {
        return orderService.findById(orderId)
                .map(o -> {
                    carService.findById(orderDto.getCarsId())
                                    .ifPresent(o::setCar);
                    carOwnerService.findById(orderDto.getCarOwnerId())
                                    .ifPresent(o::setCarOwner);
                    masterService.findById(orderDto.getMasterId())
                                    .ifPresent(o::setMaster);
                    o.setDescriptionProblem(orderDto.getDescriptionProblem());
                    o.setDateOfAcceptance(orderDto.getDateOfAcceptance());
                    o.setStatusOrder(orderDto.getStatusOrder());
                    o.setFinalCost(orderDto.getFinalCost());
                    o.setEndDate(orderDto.getEndDate());
                    o.setServices(orderDto.getServices().stream()
                            .map(serviceMapper::toModel)
                            .collect(toList()));
                    o.setProducts(orderDto.getProducts().stream()
                            .map(productMapper::toModel)
                            .collect(toList()));
                    return orderService.update(o);
                })
                .map(orderMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @PutMapping("/status-order/{orderId}")
    public ResponseEntity<OrderResponseDto> updateStatusOrder(@PathVariable Long orderId,
                                                      @RequestBody StatusOrder statusOrder) {
        return orderService.findById(orderId)
                .map(o -> {
                    o.setStatusOrder(statusOrder);
                    return orderService.update(o);
                })
                .map(orderMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/{orderId}/cost")
    public ResponseEntity<BigDecimal> getCostForOrder(@PathVariable Long orderId) {
        return orderService.findById(orderId)
                .map(orderService::getCostOrder)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }
}
