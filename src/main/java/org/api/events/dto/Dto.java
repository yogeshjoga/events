package org.api.events.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.events.constents.TypeOfPresentation;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dto {
    private String name;
    private String address;
    private Double gold;
    private Double silver;
    private Double amount;
    private String objects;
    private TypeOfPresentation typeOfPresentation;
}
