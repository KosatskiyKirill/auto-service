package autoservice.app.service.mapper;

import autoservice.app.dto.request.ServiceRequestDto;
import autoservice.app.dto.response.ServiceResponseDto;
import autoservice.app.model.Service;
import autoservice.app.service.MasterService;
import autoservice.app.service.OrderService;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapper {
    private final OrderService orderService;
    private final MasterService masterService;

    public ServiceMapper(OrderService orderService, MasterService masterService) {
        this.orderService = orderService;
        this.masterService = masterService;
    }

    public ServiceResponseDto toDto(Service service) {
        ServiceResponseDto serviceDto = new ServiceResponseDto();
        serviceDto.setId(service.getId());
        serviceDto.setCost(service.getCost());
        serviceDto.setMasterId(service.getMaster().getId());
        serviceDto.setOrderId(service.getOrder().getId());
        serviceDto.setStatus(service.getStatus());
        serviceDto.setName(service.getName());
        return serviceDto;
    }

    public Service toModel(ServiceRequestDto serviceDto) {
        Service service = new Service();
        service.setCost(serviceDto.getCost());
        service.setStatus(serviceDto.getStatus());
        orderService.findById(serviceDto.getOrderId())
                        .ifPresent(service::setOrder);
        masterService.findById(serviceDto.getMasterId())
                .ifPresent(service::setMaster);
        service.setName(serviceDto.getName());
        return service;
    }
}
