package org.api.events.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RespRecivingDTO {
    private Date createdDate;
    private Date updatedDate;
    private double amount;
    private double gold;
    private double silver;
    private String object;
}
