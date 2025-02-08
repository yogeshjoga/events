package org.api.events.dto;

import lombok.Getter;
import lombok.Setter;
import org.api.events.constents.GettingType;
import org.api.events.constents.TotalReceivedType;

@Setter
@Getter
public class TotalReceived {
    private Double Received_gm_or_INR;
    private TotalReceivedType type;
    private GettingType gettingType;
}
