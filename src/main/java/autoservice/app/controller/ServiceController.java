package autoservice.app.controller;

import autoservice.app.dto.ServiceDto;
import autoservice.app.dto.mapper.impl.MasterMapper;
import autoservice.app.dto.mapper.impl.OrderMapper;
import autoservice.app.dto.mapper.impl.ServiceMapper;
import autoservice.app.model.Services;
import autoservice.app.service.ServiceService;
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
    private final OrderMapper orderMapper;
    private final MasterMapper masterMapper;
    private final ServiceDto serviceDto;

    public ServiceController(ServiceService service, ServiceMapper serviceMapper,
                             OrderMapper orderMapper, MasterMapper masterMapper, ServiceDto serviceDto) {
        this.service = service;
        this.serviceMapper = serviceMapper;
        this.orderMapper = orderMapper;
        this.masterMapper = masterMapper;
        this.serviceDto = serviceDto;
    }

    @PostMapping
    public ResponseEntity<ServiceDto> createService(@RequestBody ServiceDto serviceDto) {
        Services newServices = service.create(serviceMapper.toModel(serviceDto));
        return ResponseEntity.ok(serviceMapper.toDto(newServices));
    }

    @PutMapping("/{serviceId}")
    public ResponseEntity<ServiceDto> updateService(@PathVariable Long serviceId,
                                                    @RequestBody ServiceDto serviceDto) {
        return service.findById(serviceId)
                .map(s -> {
                    s.setOrder(orderMapper.toModel(serviceDto.getOrderDto()));
                    s.setMaster(masterMapper.toModel(serviceDto.getMasterDto()));
                    s.setCost(serviceDto.getCost());
                    s.setStatus(serviceDto.getStatus());
                    return service.update(s);
                })
                .map(serviceMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @PutMapping("status-service/update/{serviceId}")
    public ResponseEntity<ServiceDto> updateStatusService(@PathVariable Long serviceId,
                                                          @RequestBody ServiceDto serviceDto) {
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
