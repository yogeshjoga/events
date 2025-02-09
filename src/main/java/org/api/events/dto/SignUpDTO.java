package org.api.events.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpDTO {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
}
