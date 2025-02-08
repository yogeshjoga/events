package org.api.events.service.receivingservice;

import org.api.events.models.Receiving;

import java.util.List;

public interface IReceivingService {

    Receiving saveReceiving(Receiving receiving);

    List<Receiving> getAllRecivings();
}
