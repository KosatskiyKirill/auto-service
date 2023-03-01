package autoservice.app.controller;

import autoservice.app.dto.OrderDto;
import autoservice.app.dto.ProductDto;
import autoservice.app.dto.mapper.impl.MasterMapper;
import autoservice.app.dto.mapper.impl.OrderMapper;
import autoservice.app.model.Order;
import autoservice.app.model.enums.StatusOrder;
import autoservice.app.service.OrderService;
import autoservice.app.dto.mapper.impl.CarMapper;
import autoservice.app.dto.mapper.impl.CarOwnerMapper;
import autoservice.app.dto.mapper.impl.ProductMapper;
import autoservice.app.dto.mapper.impl.ServiceMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ProductMapper productMapper;
    private final OrderMapper orderMapper;
    private final CarMapper carMapper;
    private final ServiceMapper serviceMapper;
    private final CarOwnerMapper carOwnerMapper;
    private final MasterMapper masterMapper;

    public OrderController(OrderService orderService, ProductMapper productMapper,
                           OrderMapper orderMapper, CarMapper carMapper, ServiceMapper serviceMapper,
                           CarOwnerMapper carOwnerMapper, MasterMapper masterMapper) {
        this.orderService = orderService;
        this.productMapper = productMapper;
        this.orderMapper = orderMapper;
        this.carMapper = carMapper;
        this.serviceMapper = serviceMapper;
        this.carOwnerMapper = carOwnerMapper;
        this.masterMapper = masterMapper;
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        Order newOrder = orderService.create(orderMapper.toModel(orderDto));
        return ResponseEntity.ok(orderMapper.toDto(newOrder));
    }

    @PostMapping("/add-product-to-order/{orderId}")
    public ResponseEntity<OrderDto> addProductToOrder(@PathVariable Long orderId,
                                                      @RequestBody ProductDto productDto) {
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
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long orderId,
                                                @RequestBody OrderDto orderDto) {
        return orderService.findById(orderId)
                .map(o -> {
                    o.setCars(carMapper.toModel(orderDto.getCarsDto()));
                    o.setCarOwner(carOwnerMapper.toModel(orderDto.getCarOwnerDto()));
                    o.setMaster(masterMapper.toModel(orderDto.getMasterDto()));
                    o.setDescriptionProblem(orderDto.getDescriptionProblem());
                    o.setDateOfAcceptance(orderDto.getDateOfAcceptance());
                    o.setStatusOrder(orderDto.getStatusOrder());
                    o.setFinalCost(orderDto.getFinalCost());
                    o.setEndDate(orderDto.getEndDate());
                    o.setServices(orderDto.getServices().stream()
                            .map(serviceMapper::toModel)
                            .collect(toList()));
                    o.setProducts(orderDto.getProductsDto().stream()
                            .map(productMapper::toModel)
                            .collect(toList()));
                    return orderService.update(o);
                })
                .map(orderMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @PutMapping("status-order/update/{orderId}")
    public ResponseEntity<OrderDto> updateStatusOrder(@PathVariable Long orderId,
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
    public ResponseEntity<Double> getCostForOrder(@PathVariable Long orderId) {
        return orderService.findById(orderId)
                .map(orderService::getCostOrder)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }
}
