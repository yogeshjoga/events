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
    private double gold;
    private double silver;
    private double amount;
    private String objects;

    // code for this
    private TypeOfPresentation typeOfPresentation;
}
