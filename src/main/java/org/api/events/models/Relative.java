package org.api.events.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.api.events.constents.VerficationState;
import java.util.List;

@Setter
@Getter
@Entity(name = "relative")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "user"})
public class Relative extends BaseModel {
    private String firstName;
    private String lastName;
    private String city;
    private String address;
    /**
     * The @Transient annotation in Java Persistence API (JPA) is used to mark a field as non-persistent.
     * This means that the field will not be mapped to any database column,
     * and its value will not be stored in the database.
     * Essentially, it tells the JPA provider to ignore this field during persistence operations
     * (such as INSERT, UPDATE, and SELECT).
     */
    @Transient
    private String email; //---
    @Transient
    private String password; //----
    @Transient
    private String userName;//---
    private String phone; // null

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "relative")
    @JsonIgnoreProperties("relative")
    private List<Presentation> presentations;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "relative")
    @JsonIgnoreProperties("relative")
    private List<Receiving> receivings;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"relatives", "presentations", "receivings"})
    private User user;


//---------
    @Enumerated(EnumType.STRING)
    private VerficationState state;

    @PrePersist
    protected void onCreate() {
        if (state == null) {
            state = VerficationState.VERFICATION_IN_PROGRESS;
        }
    }

}
