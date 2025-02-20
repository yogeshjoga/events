package org.api.events.utils;

import org.api.events.dto.Dto;
import org.api.events.dto.RespPresentationDto;
import org.api.events.dto.RespRecivingDTO;
import org.api.events.models.Presentation;
import org.api.events.models.Receiving;
import org.api.events.models.Relative;
import org.api.events.models.User;

import java.util.List;
import java.util.UUID;

public interface IDtoMapper {
     Relative dtoToRelative(Dto dto, User user);
     Presentation dtoToPresentation(Dto dto, User user);
     List<RespPresentationDto> presentationToRespDtoList(List<Presentation> presentations);
     Receiving dtoToReceiving(Dto dto,User user, Relative relative);
     List<RespRecivingDTO> recivingToRespDtoList(List<Receiving> receivings);
}
