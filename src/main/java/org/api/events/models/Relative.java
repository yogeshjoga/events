package org.api.events.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@Entity(name = "relative")
public class Relative extends BaseModel {
    private String firstName;
    private String lastName;
    private String city;
   // private String name;
    private String address;
    private String phone; // null

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Presentation> presentations;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Receiving> receivings;
}
