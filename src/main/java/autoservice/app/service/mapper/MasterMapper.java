package autoservice.app.service.mapper;

import autoservice.app.dto.request.MasterRequestDto;
import autoservice.app.dto.response.MasterResponseDto;
import autoservice.app.model.Master;
import org.springframework.stereotype.Component;

@Component
public class MasterMapper {
    public MasterResponseDto toDto(Master master) {
        MasterResponseDto masterDto = new MasterResponseDto();
        masterDto.setId(master.getId());
        masterDto.setFullName(master.getFullName());
        return masterDto;
    }

    public Master toModel(MasterRequestDto masterDto) {
        Master master = new Master();
        master.setFullName(masterDto.getFullName());
        return master;
    }
}
