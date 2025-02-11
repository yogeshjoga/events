package org.api.events.dto;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
public class SortingDTO {
    private UUID id;
    private String name;
    private String fistName;
    private String lastName;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
