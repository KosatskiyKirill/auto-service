package autoservice.app.service.mapper;

import static java.util.stream.Collectors.toList;

import autoservice.app.dto.request.OrderRequestDto;
import autoservice.app.dto.response.OrderResponseDto;
import autoservice.app.model.Order;
import autoservice.app.service.CarOwnerService;
import autoservice.app.service.MasterService;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final ServiceMapper serviceMapper;
    private final ProductMapper productMapper;
    private final CarOwnerService carOwnerService;
    private final MasterService masterService;

    public OrderMapper(ServiceMapper serviceMapper, ProductMapper productMapper,
                       CarOwnerService carOwnerService, MasterService masterService) {
        this.serviceMapper = serviceMapper;
        this.productMapper = productMapper;
        this.carOwnerService = carOwnerService;
        this.masterService = masterService;
    }

    public OrderResponseDto toDto(Order order) {
        OrderResponseDto orderDto = new OrderResponseDto();
        orderDto.setId(order.getId());
        orderDto.setCarsId(order.getCar().getId());
        orderDto.setCarOwnerId(order.getCarOwner().getId());
        orderDto.setMasterId(order.getMaster().getId());
        orderDto.setDescriptionProblem(order.getDescriptionProblem());
        orderDto.setDateOfAcceptance(order.getDateOfAcceptance());
        orderDto.setServices(order.getServices().stream()
                .map(serviceMapper::toDto)
                .collect(toList()));
        orderDto.setProducts(order.getProducts().stream()
                .map(productMapper::toDto)
                .collect(toList()));
        orderDto.setStatusOrder(order.getStatusOrder());
        orderDto.setFinalCost(order.getFinalCost());
        orderDto.setEndDate(order.getEndDate());
        return orderDto;
    }

    public Order toModel(OrderRequestDto orderDto) {
        Order order = new Order();
        order.setDescriptionProblem(orderDto.getDescriptionProblem());
        order.setDateOfAcceptance(orderDto.getDateOfAcceptance());
        order.setServices(orderDto.getServices().stream()
                .map(serviceMapper::toModel)
                .collect(toList()));
        order.setProducts(orderDto.getProducts().stream()
                .map(productMapper::toModel)
                .collect(toList()));
        order.setStatusOrder(orderDto.getStatusOrder());
        order.setFinalCost(orderDto.getFinalCost());
        order.setEndDate(orderDto.getEndDate());
        carOwnerService.findById(orderDto.getCarOwnerId())
                .ifPresent(order::setCarOwner);
        masterService.findById(orderDto.getMasterId())
                .ifPresent(order::setMaster);
        return order;
    }
}
