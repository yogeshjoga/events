package org.api.events.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import lombok.*;


@Entity(name = "silver_price")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SilverRates extends BaseModel{
    @JsonProperty("timestamp")
    private long timestamp;
    @JsonProperty("metal")
    private String metal;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("exchange")
    private String exchange;
    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("prev_close_price")
    private double prev_close_price;
    @JsonProperty("open_price")
    private double open_price;
    @JsonProperty("low_price")
    private double low_price;
    @JsonProperty("high_price")
    private double high_price;
    @JsonProperty("open_time")
    private long open_time;
    @JsonProperty("price")
    private double price;
    @JsonProperty("ch")
    private double ch;
    @JsonProperty("chp")
    private double chp;
    @JsonProperty("ask")
    private double ask;
    @JsonProperty("bid")
    private double bid;
    @JsonProperty("price_gram_24k")
    private double price_gram_24k;
    @JsonProperty("price_gram_22k")
    private double price_gram_22k;
    @JsonProperty("price_gram_21k")
    private double price_gram_21k;
    @JsonProperty("price_gram_20k")
    private double price_gram_20k;
    @JsonProperty("price_gram_18k")
    private double price_gram_18k;
    @JsonProperty("price_gram_16k")
    private double price_gram_16k;
    @JsonProperty("price_gram_14k")
    private double price_gram_14k;
    @JsonProperty("price_gram_10k")
    private double price_gram_10k;
}