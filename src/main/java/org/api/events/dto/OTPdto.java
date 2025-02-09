package org.api.events.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OTPdto {
    private String otp;
    private String email;
}
