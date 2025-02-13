package org.api.events.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity(name = "receiving")
public class Receiving extends BaseModel {
    private double gold_in_gm;
    private double silver_in_gm;
    private double amount;
    private String objects;

    // Added foreign key into Receiving Table
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "relative_id")
    private Relative relative;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
