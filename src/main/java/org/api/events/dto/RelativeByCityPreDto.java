package org.api.events.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelativeByCityPreDto {
    private String full_name;
    private String userName;
    private String email;
    private String phone;
    private double gold_in_gm;
    private double silver_in_gm;
    private double amount;
    private String gift;
}
