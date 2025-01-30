package org.api.events.dto;


import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
public class RespPresentationDto {
    private Date createdDate;
    private Date updatedDate;
    private double amount;
    private double gold;
    private double silver;
    private String object;
}
