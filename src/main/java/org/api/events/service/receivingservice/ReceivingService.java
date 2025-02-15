package org.api.events.service.receivingservice;

import org.api.events.models.Receiving;
import org.api.events.repo.ReceivingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReceivingService implements IReceivingService {



    @Autowired
    private ReceivingRepo receivingRepo;


    @Override
    public Receiving saveReceiving(Receiving receiving) {
        return receivingRepo.save(receiving);
    }

    @Override
    public List<Receiving> getAllRecivings(UUID userId){
        return receivingRepo.findAll(userId);
    }


    @Override
    public Double getTotalGold(UUID userId){
        return receivingRepo.findTotalGold_in_gm(userId);
    }

    @Override
    public Double getTotalSliver(UUID userId){
        return receivingRepo.findTotalSilver_in_gm(userId);
    }

    @Override
    public Double getTotalAmount(UUID userId){
        return receivingRepo.findTotalAmount(userId);
    }




}
