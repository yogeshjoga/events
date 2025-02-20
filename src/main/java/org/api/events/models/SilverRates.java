package org.api.events.models;

import jakarta.persistence.Entity;
import lombok.*;


@Entity(name = "silver_price")
@Setter
@Getter
@ToString
// @AllArgsConstructor
@NoArgsConstructor
public class SilverRates extends BaseModel{

    private String metal;
    private String currency;
    private String exchange;
    private String symbol;
    private double prev_close_price;
    private double open_price;
    private double low_price;
    private double high_price;
    private long open_time;
    private double price;
    private double ch;
    private double chp;
    private double ask;
    private double bid;
    private double price_gram_24k;
    private double price_gram_22k;
    private double price_gram_21k;
    private double price_gram_20k;
    private double price_gram_18k;
    private double price_gram_16k;
    private double price_gram_14k;
    private double price_gram_10k;
}