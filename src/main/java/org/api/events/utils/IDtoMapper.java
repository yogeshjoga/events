package org.api.events.utils;

import org.api.events.dto.Dto;
import org.api.events.models.Presentation;
import org.api.events.models.Relative;

public interface IDtoMapper {
     Relative dtoToRelative(Dto dto);
     Presentation dtoToPresentation(Dto dto);
}
