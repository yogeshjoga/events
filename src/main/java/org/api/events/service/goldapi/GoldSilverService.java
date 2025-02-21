package org.api.events.service.goldapi;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.api.events.models.GoldRates;
import org.api.events.models.SilverRates;
import org.api.events.repo.GoldRateRepo;
import org.api.events.repo.SilverRateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.CacheRequest;
import java.util.Objects;

@Service
public class GoldSilverService implements IGoldSilverService {


    /**
     * Website link api's site
     * <h1><b>https://www.goldapi.io/dashboard</b></h1>
     */

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private GoldRateRepo goldRateRepo;

    @Autowired
    private SilverRateRepo silverRateRepo;

    private static final String GOLD_URL = "https://www.goldapi.io/api/XAU/INR";
    private static final String SILVER_URL = "https://www.goldapi.io/api/XAG/INR";
    private static final String ACCESS_TOKEN = "goldapi-13esmdsm7dmgjdb-io";

    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders headers = new HttpHeaders();


    /**
     *
     * @return
     */
    @Override
    public ResponseEntity<GoldRates> getGoldRateAPI() {
        headers.set("x-access-token", ACCESS_TOKEN);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<GoldRates> response = restTemplate.exchange(GOLD_URL, HttpMethod.GET, entity, GoldRates.class);
     //   GoldRates goldRates = mapper.convertValue(response.getBody(), GoldRates.class);
      //  goldRateRepo.deleteAll();
        goldRateRepo.save(Objects.requireNonNull(response.getBody()));

        return  response;
    }


    /**
     *
     * @return
     */
    @Override
    public ResponseEntity<SilverRates> getSilverRateAPI() {
        headers.set("x-access-token", ACCESS_TOKEN);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<SilverRates> response = restTemplate.exchange(SILVER_URL, HttpMethod.GET, entity, SilverRates.class);
      //  SilverRates silverRates = mapper.convertValue(response.getBody(), SilverRates.class);
       // silverRateRepo.deleteAll();
        silverRateRepo.save(Objects.requireNonNull(response.getBody()));

        return response;
    }

   // "XAU" GOLD
    @Override
    public final GoldRates getGoldRates() {
//        if(goldRateRepo.findAll().isEmpty()) {
//            getGoldRateAPI();
//        }
        return goldRateRepo.findAll().get(0);
    }

    // "XAG" SILVER
    @Override
    public final SilverRates getSilverRates() {
//        if(silverRateRepo.findAll().isEmpty()) {
//            getSilverRateAPI();
//        }
        return silverRateRepo.findAll().get(0);
    }


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

//{23 items
//        "timestamp":1740073680
//        "metal":"XAU"
//        "currency":"USD"
//        "exchange":"FOREXCOM"
//        "symbol":"FOREXCOM:XAUUSD"
//        "prev_close_price":2933.11
//        "open_price":2933.11
//        "low_price":2924.17
//        "high_price":2954.965
//        "open_time":1740009600
//        "price":2944.58
//        "ch":11.47
//        "chp":0.39
//        "ask":2945.06
//        "bid":2944.14
//        "price_gram_24k":94.6704
//        "price_gram_22k":86.7812
//        "price_gram_21k":82.8366
//        "price_gram_20k":78.892
//        "price_gram_18k":71.0028
//        "price_gram_16k":63.1136
//        "price_gram_14k":55.2244
//        "price_gram_10k":39.446
//        }