package org.api.events.service.receivingservice;

import org.api.events.dto.TopFiveRelatives;
import org.api.events.models.Receiving;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface IReceivingService {

    Receiving saveReceiving(Receiving receiving);
    List<Receiving> getAllRecivings(@Param("userId") UUID userId);
    Double getTotalGold(@Param("userId") UUID userId);
    Double getTotalAmount(@Param("userId") UUID userId);
    Double getTotalSliver(@Param("userId") UUID userId);


    List<TopFiveRelatives> getSilverWithNameTopFiveRelatives(UUID userId);
    List<TopFiveRelatives> getGoldWithNameTopFiveRelatives(UUID userId);
}
