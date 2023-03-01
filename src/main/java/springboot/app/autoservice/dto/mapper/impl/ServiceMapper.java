package springboot.app.autoservice.dto.mapper.impl;

import org.springframework.stereotype.Component;
import springboot.app.autoservice.dto.ServiceDto;
import springboot.app.autoservice.dto.mapper.DtoMapper;
import springboot.app.autoservice.model.Services;

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
