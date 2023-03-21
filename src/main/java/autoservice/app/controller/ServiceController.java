package autoservice.app.controller;

import autoservice.app.dto.request.ServiceRequestDto;
import autoservice.app.dto.response.ServiceResponseDto;
import autoservice.app.model.Service;
import autoservice.app.service.MasterService;
import autoservice.app.service.OrderService;
import autoservice.app.service.ServiceService;
import autoservice.app.service.mapper.ServiceMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/services")
public class ServiceController {
    private final ServiceService service;
    private final ServiceMapper serviceMapper;
    private final OrderService orderService;
    private final MasterService masterService;

    public ServiceController(ServiceService service, ServiceMapper serviceMapper,
                             OrderService orderService, MasterService masterService) {
        this.service = service;
        this.serviceMapper = serviceMapper;
        this.orderService = orderService;
        this.masterService = masterService;
    }

    @PostMapping
    public ResponseEntity<ServiceResponseDto> createService(@RequestBody
                                                                ServiceRequestDto serviceDto) {
        Service newService = service.create(serviceMapper.toModel(serviceDto));
        return ResponseEntity.ok(serviceMapper.toDto(newService));
    }

    @PutMapping("/{serviceId}")
    public ResponseEntity<ServiceResponseDto> updateService(@PathVariable Long serviceId,
                                                            @RequestBody
                                                            ServiceRequestDto serviceDto) {
        return service.findById(serviceId)
                .map(s -> {
                    orderService.findById(serviceDto.getOrderId())
                                    .ifPresent(s::setOrder);
                    masterService.findById(serviceDto.getMasterId())
                                    .ifPresent(s::setMaster);
                    s.setCost(serviceDto.getCost());
                    s.setStatus(serviceDto.getStatus());
                    return service.update(s);
                })
                .map(serviceMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @PutMapping("status-service/{serviceId}")
    public ResponseEntity<ServiceResponseDto> updateStatusService(@PathVariable Long serviceId,
                                                          @RequestBody
                                                          ServiceRequestDto serviceDto) {
        return service.findById(serviceId)
                .map(s -> {
                    s.setStatus(serviceDto.getStatus());
                    return service.update(s);
                })
                .map(serviceMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }
}
