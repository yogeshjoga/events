package org.api.events.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class RespRecivingDTO {
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private double amount;
    private double gold;
    private double silver;
    private String object;
}
