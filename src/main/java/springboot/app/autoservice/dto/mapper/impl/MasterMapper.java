package springboot.app.autoservice.dto.mapper.impl;

import org.springframework.stereotype.Component;
import springboot.app.autoservice.dto.MasterDto;
import springboot.app.autoservice.dto.mapper.DtoMapper;
import springboot.app.autoservice.model.Master;

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
