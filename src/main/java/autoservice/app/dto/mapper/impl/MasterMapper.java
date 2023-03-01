package autoservice.app.dto.mapper.impl;

import autoservice.app.dto.MasterDto;
import autoservice.app.model.Master;
import autoservice.app.dto.mapper.DtoMapper;
import org.springframework.stereotype.Component;

@Component
public class MasterMapper implements DtoMapper<MasterDto, Master> {
    @Override
    public MasterDto toDto(Master master) {
        MasterDto masterDto = new MasterDto();
        masterDto.setId(master.getId());
        masterDto.setFullName(master.getFullName());
        return masterDto;
    }

    @Override
    public Master toModel(MasterDto masterDto) {
        Master master = new Master();
        master.setFullName(masterDto.getFullName());
        return master;
    }
}
