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
    private String firstName;
    private String lastName;
    private String city;
    private String address;
    private String phone; // null
   // private String name;
    private double gold;
    private double silver;
    private double amount;
    private String objects;
    // code for this
    private TypeOfPresentation typeOfPresentation;
}
