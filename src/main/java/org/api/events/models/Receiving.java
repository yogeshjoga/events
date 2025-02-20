package org.api.events.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity(name = "receiving")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "relative", "user"})
public class Receiving extends BaseModel {
    private double gold_in_gm;
    private double silver_in_gm;
    private double amount;
    private String objects;

    // Added foreign key into Receiving Table
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "relative_id")
  //  @JsonIgnoreProperties("receivings")
    private Relative relative;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
   // @JsonIgnoreProperties({"receivings", "relatives"})
    private User user;
}
