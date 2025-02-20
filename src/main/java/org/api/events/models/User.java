package org.api.events.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.api.events.constents.VerficationState;

import java.util.List;

@Setter
@Getter
@Entity(name = "user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "relative", "presentations", "receivings"})
public class User extends BaseModel {
    private String firstName;
    private String lastName;
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


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy =  "user")
    @JsonIgnoreProperties("user")
    private List<Relative> relative;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Presentation> presentation;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Receiving> receiving;

}

/**
 *
 * one User have many relatives
 * User to Relative ONE TO MANY
 * User to
 *
 */