package org.api.events.service.presentationservice;


import org.api.events.models.Presentation;
import org.api.events.repo.PresentationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class PresentationService implements IPresentationService {

    @Autowired
    private PresentationRepo presentationRepo;

    @Override
    public Presentation savePresentation(Presentation presentation) {
        return presentationRepo.save(presentation);
    }

    @Override
    public List<Presentation> getAll(UUID userId) {
        return presentationRepo.findAll(userId);
    }

    @Override
    public Double getTotalGold(UUID userId){
        return presentationRepo.findTotalGold_in_gm(userId);
    }

    @Override
    public Double getTotalSliver(UUID userId){
        return presentationRepo.findTotalSilver_in_gm(userId);
    }

    @Override
    public Double getTotalAmount(UUID userId){
        return presentationRepo.findTotalAmount(userId);
    }
}
