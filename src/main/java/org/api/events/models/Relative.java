package org.api.events.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity(name = "relative")
public class Relative extends BaseModel {
    private String name;
    private String address;
    private String phone; // null

    @OneToMany(mappedBy = "relative", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Presentation> presentations = new ArrayList<>();

    @OneToMany(mappedBy = "relative", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Receiving> receivings = new ArrayList<>();
}
