package org.api.events.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiftsFromRelatives {
    private String full_name;
   // private String email;
    private String phone;
    private long total_gifts;
}
