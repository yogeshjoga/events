package org.api.events.utils;

import org.api.events.dto.Dto;
import org.api.events.dto.RespPresentationDto;
import org.api.events.models.Presentation;
import org.api.events.models.Relative;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Component
public class DtoMapper implements IDtoMapper {

    private static final Logger log = LoggerFactory.getLogger(DtoMapper.class);

    @Override
    public Relative dtoToRelative(Dto dto) {
        Relative relative = new Relative();
        relative.setId(UUID.randomUUID());
     //   relative.setName(dto.getName());
        relative.setFirstName(dto.getFirstName());
        relative.setLastName(dto.getLastName());
        relative.setCity(dto.getCity());
        relative.setAddress(dto.getAddress());
        relative.setPhone(dto.getPhone());
        log.info("\u001B[1;32m :: DTO TO RELATIVE CREATED :: \u001B[0m");
        return relative;
    }

    // user here Generics
    @Override
    public Presentation dtoToPresentation(Dto dto) {
        Presentation presentation = new Presentation();
        presentation.setId(UUID.randomUUID());
        presentation.setAmount(dto.getAmount());
        presentation.setObjects(dto.getObjects());
        presentation.setGold_in_gm(dto.getGold());
        presentation.setSilver_in_gm(dto.getSilver());
        log.info("\u001B[1;32m :: DTO TO PRESENTATION CREATED :: \u001B[0m");
        return presentation;
    }



    @Override
    public List<RespPresentationDto> presentationToRespDtoList(List<Presentation> presentations) {
        List<RespPresentationDto> respDtos = new ArrayList<>();
        for (Presentation presentation : presentations) {
            RespPresentationDto respDto = new RespPresentationDto();
            respDto.setAmount(presentation.getAmount());
            respDto.setGold(presentation.getGold_in_gm());
            respDto.setSilver(presentation.getSilver_in_gm());
            respDto.setObject(presentation.getObjects());
            respDto.setCreatedDate(presentation.getCreated());
            respDto.setUpdatedDate(presentation.getUpdated());
            respDtos.add(respDto);
        }
        log.info("\u001B[1;32m :: LIST OF PRESENTATIONS TO RESPONCE_PRESENTATION_DTO CREATED :: \u001B[0m");
        return respDtos;
    }

}
