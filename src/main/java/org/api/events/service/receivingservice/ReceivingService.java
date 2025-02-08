package org.api.events.service.receivingservice;

import org.api.events.models.Receiving;
import org.api.events.repo.ReceivingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceivingService implements IReceivingService {



    @Autowired
    private ReceivingRepo receivingRepo;


    @Override
    public Receiving saveReceiving(Receiving receiving) {
        return receivingRepo.save(receiving);
    }

    @Override
    public List<Receiving> getAllRecivings(){
        return receivingRepo.findAll();
    }


    @Override
    public Double getTotalGold(){
        return receivingRepo.findTotalGold_in_gm();
    }

    @Override
    public Double getTotalSliver(){
        return receivingRepo.findTotalSilver_in_gm();
    }

    @Override
    public Double getTotalAmount(){
        return receivingRepo.findTotalAmount();
    }




}
