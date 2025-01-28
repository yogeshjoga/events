package org.api.events.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponceRelativeDto {
    private String firstName;
    private String lastName;
    private String city;
    // private String name;
    private String address;
    private String phone; // null
}
