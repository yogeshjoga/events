package org.api.events.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.api.events.models.Presentation;
import org.api.events.models.Receiving;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRelativeData {
    private String firstName;
    private String lastName;
    private String city;
    private String address;
    private String phone;
    private List<Presentation> presentations;
    private List<Receiving> receivings;

}
