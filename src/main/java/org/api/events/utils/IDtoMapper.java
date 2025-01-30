package org.api.events.utils;

import org.api.events.dto.Dto;
import org.api.events.dto.RespPresentationDto;
import org.api.events.models.Presentation;
import org.api.events.models.Relative;

import java.util.List;

public interface IDtoMapper {
     Relative dtoToRelative(Dto dto);
     Presentation dtoToPresentation(Dto dto);
     List<RespPresentationDto> presentationToRespDtoList(List<Presentation> presentations);
}
