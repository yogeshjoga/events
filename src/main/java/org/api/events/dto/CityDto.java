package org.api.events.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class CityDto {
    private String full_name; // add both firstname and lastname
    private String city;
    private String phone; // null
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private double amount;
    private double gold;
    private double silver;
    private String object;
}
