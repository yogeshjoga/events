package org.api.events.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SilverFromRelatives {
    private String full_name;
    private String email;
    private String phone;
    private double total_silver_in_gm;
}
