package org.api.events.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.api.events.constents.VerficationState;

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

    @Column(unique = true, nullable = true)
    private String phone; // null

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private VerficationState state;

    @PrePersist
    protected void onCreate() {
        if (state == null) {
            state = VerficationState.VERFICATION_IN_PROGRESS;
        }
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "relative")
    private List<Presentation> presentations;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "relative")
    private List<Receiving> receivings;

//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "relative")
//    private OTP otp;
}
