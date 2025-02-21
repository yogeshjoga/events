package org.api.events.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import lombok.*;

@Entity(name = "gold_price")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GoldRates extends BaseModel{

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

//{23 items
//        "timestamp":1740073210
//        "metal":"XAG"
//        "currency":"INR"
//        "exchange":"IDC"
//        "symbol":"FX_IDC:XAGINR"
//        "prev_close_price":2828.9
//        "open_price":2840.9
//        "low_price":2837.4
//        "high_price":2873.7
//        "open_time":1740009600
//        "price":2858.1
//        "ch":29.2
//        "chp":1.03
//        "ask":2863.1
//        "bid":2858.1
//        "price_gram_24k":91.89
//        "price_gram_22k":84.2325
//        "price_gram_21k":80.4038
//        "price_gram_20k":76.575
//        "price_gram_18k":68.9175
//        "price_gram_16k":61.26
//        "price_gram_14k":53.6025
//        "price_gram_10k":38.2875
//        }

//@JsonProperty("timestamp")
//private long timestamp;
//@JsonProperty("metal")
//private String metal;
//@JsonProperty("currency")
//private String currency;
//@JsonProperty("exchange")
//private String exchange;
//@JsonProperty("symbol")
//private String symbol;
//@JsonProperty("prev_close_price")
//private double prevClosePrice;
//@JsonProperty("open_price")
//private double openPrice;
//@JsonProperty("low_price")
//private double lowPrice;
//@JsonProperty("high_price")
//private double highPrice;
//@JsonProperty("open_time")
//private long openTime;
//@JsonProperty("price")
//private double price;
//@JsonProperty("ch")
//private double ch;
//@JsonProperty("chp")
//private double chp;
//@JsonProperty("ask")
//private double ask;
//@JsonProperty("bid")
//private double bid;
//@JsonProperty("price_gram_24k")
//private double priceGram24k;
//@JsonProperty("price_gram_22k")
//private double priceGram22k;
//@JsonProperty("price_gram_21k")
//private double priceGram21k;
//@JsonProperty("price_gram_20k")
//private double priceGram20k;
//@JsonProperty("price_gram_18k")
//private double priceGram18k;
//@JsonProperty("price_gram_16k")
//private double priceGram16k;
//@JsonProperty("price_gram_14k")
//private double priceGram14k;
//@JsonProperty("price_gram_10k")
//private double