package autoservice.app.service.mapper;

import autoservice.app.dto.request.ServiceRequestDto;
import autoservice.app.dto.response.ServiceResponseDto;
import autoservice.app.model.Services;
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

    public ServiceResponseDto toDto(Services services) {
        ServiceResponseDto serviceDto = new ServiceResponseDto();
        serviceDto.setId(services.getId());
        serviceDto.setCost(services.getCost());
        serviceDto.setMasterId(services.getMaster().getId());
        serviceDto.setOrderId(services.getOrder().getId());
        serviceDto.setStatus(services.getStatus());
        return serviceDto;
    }

    public Services toModel(ServiceRequestDto serviceDto) {
        Services service = new Services();
        service.setCost(serviceDto.getCost());
        service.setStatus(serviceDto.getStatus());
        orderService.findById(serviceDto.getOrderId())
                        .ifPresent(service::setOrder);
        masterService.findById(serviceDto.getMasterId())
                .ifPresent(service::setMaster);
        return service;
    }
}
