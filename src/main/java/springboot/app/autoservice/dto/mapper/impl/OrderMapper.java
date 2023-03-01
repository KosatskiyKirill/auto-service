package springboot.app.autoservice.dto.mapper.impl;

import org.springframework.stereotype.Component;
import springboot.app.autoservice.dto.OrderDto;
import springboot.app.autoservice.dto.mapper.DtoMapper;
import springboot.app.autoservice.model.Order;

import static java.util.stream.Collectors.toList;

@Component
public class OrderMapper implements DtoMapper <OrderDto, Order> {
    private final CarMapper carMapper;
    private final CarOwnerMapper carOwnerMapper;
    private final MasterMapper masterMapper;
    private final ServiceMapper serviceMapper;
    private final ProductMapper productMapper;

    public OrderMapper(CarMapper carMapper, CarOwnerMapper carOwnerMapper,
                       MasterMapper masterMapper, ServiceMapper serviceMapper, ProductMapper productMapper) {
        this.carMapper = carMapper;
        this.carOwnerMapper = carOwnerMapper;
        this.masterMapper = masterMapper;
        this.serviceMapper = serviceMapper;
        this.productMapper = productMapper;
    }

    @Override
    public OrderDto toDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setCarsDto(carMapper.toDto(order.getCars()));
        dto.setCarOwnerDto(carOwnerMapper.toDto(order.getCarOwner()));
        dto.setMasterDto(masterMapper.toDto(order.getMaster()));
        dto.setDescriptionProblem(order.getDescriptionProblem());
        dto.setDateOfAcceptance(order.getDateOfAcceptance());
        dto.setServicesDto(order.getServices().stream()
                .map(serviceMapper::toDto)
                .collect(toList()));
        dto.setProductsDto(order.getProducts().stream()
                .map(productMapper::toDto)
                .collect(toList()));
        dto.setStatusOrder(order.getStatusOrder());
        dto.setFinalCost(order.getFinalCost());
        dto.setEndDate(order.getEndDate());
        return dto;
    }

    @Override
    public Order toModel(OrderDto dto) {
        Order order = new Order();
        order.setDescriptionProblem(dto.getDescriptionProblem());
        order.setDateOfAcceptance(dto.getDateOfAcceptance());
        order.setServices(dto.getServices().stream()
                .map(serviceMapper::toModel)
                .collect(toList()));
        order.setProducts(dto.getProductsDto().stream()
                .map(productMapper::toModel)
                .collect(toList()));
        order.setStatusOrder(dto.getStatusOrder());
        order.setFinalCost(dto.getFinalCost());
        order.setEndDate(dto.getEndDate());
        order.setCarOwner(carOwnerMapper.toModel(dto.getCarOwnerDto()));
        order.setMaster(masterMapper.toModel(dto.getMasterDto()));
        return order;
    }
}
