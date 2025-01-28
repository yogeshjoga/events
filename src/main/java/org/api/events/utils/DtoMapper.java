package org.api.events.utils;

import org.api.events.dto.Dto;
import org.api.events.models.Presentation;
import org.api.events.models.Relative;
import org.springframework.stereotype.Component;

import java.util.Random;


@Component
public class DtoMapper {

    Random random = new Random();

    public Relative dtoToRelative(Dto dto) {
        Relative relative = new Relative();
        relative.setId(random.nextLong());
        relative.setName(dto.getName());
        relative.setAddress(dto.getAddress());
        return relative;
    }

    public Presentation dtoToPresentation(Dto dto) {
        Presentation presentation = new Presentation();
        long id = random.nextLong();
        presentation.setId(id);
        presentation.setAmount(dto.getAmount());
        presentation.setObjects(dto.getObjects());
        presentation.setGold_in_gm(dto.getGold());
        presentation.setSilver_in_gm(dto.getSilver());
        return presentation;
    }
}
