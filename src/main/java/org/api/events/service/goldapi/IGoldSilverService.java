package org.api.events.service.goldapi;

import org.api.events.models.GoldRates;
import org.api.events.models.SilverRates;
import org.springframework.http.ResponseEntity;

public interface IGoldSilverService {
    ResponseEntity<String> getGoldRateAPI();
    ResponseEntity<String> getSilverRateAPI();
    GoldRates getGoldRates();
    SilverRates getSilverRates();
}
