package org.api.events.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.api.events.constents.VerficationState;

import java.util.List;

@Setter
@Getter
@Entity(name = "user")
public class User extends BaseModel {
    @Column(unique = true, nullable = false)
    private String userName;
    @Column(unique = true, nullable = false)
    private String password;
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


    @OneToMany(mappedBy =  "user")
    private List<Relative> relative;
    @OneToMany(mappedBy = "user")
    private List<Presentation> presentation;
    @OneToMany(mappedBy = "user")
    private List<Receiving> receiving;

}

/**
 *
 * one User have many relatives
 * User to Relative ONE TO MANY
 * User to
 *
 */