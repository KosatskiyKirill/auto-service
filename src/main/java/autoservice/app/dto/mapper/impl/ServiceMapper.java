package autoservice.app.dto.mapper.impl;

import autoservice.app.dto.ServiceDto;
import autoservice.app.model.Services;
import autoservice.app.dto.mapper.DtoMapper;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapper implements DtoMapper<ServiceDto, Services> {
    private final MasterMapper masterMapper;
    private final OrderMapper orderMapper;

    public ServiceMapper(MasterMapper masterMapper, OrderMapper orderMapper) {
        this.masterMapper = masterMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public ServiceDto toDto(Services services) {
        ServiceDto dto = new ServiceDto();
        dto.setId(services.getId());
        dto.setCost(services.getCost());
        dto.setMasterDto(masterMapper.toDto(services.getMaster()));
        dto.setOrderDto(orderMapper.toDto(services.getOrder()));
        dto.setStatus(services.getStatus());
        return dto;
    }

    @Override
    public Services toModel(ServiceDto dto) {
        Services service = new Services();
        service.setMaster(masterMapper.toModel(dto.getMasterDto()));
        service.setCost(dto.getCost());
        service.setStatus(dto.getStatus());
        return service;
    }
}
