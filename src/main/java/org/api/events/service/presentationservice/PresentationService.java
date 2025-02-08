package org.api.events.service.presentationservice;


import org.api.events.models.Presentation;
import org.api.events.repo.PresentationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PresentationService implements IPresentationService {

    @Autowired
    private PresentationRepo presentationRepo;

    @Override
    public Presentation savePresentation(Presentation presentation) {
        return presentationRepo.save(presentation);
    }

    @Override
    public List<Presentation> getAll() {
        return presentationRepo.findAll();
    }

    @Override
    public Double getTotalGold(){
        return presentationRepo.findTotalGold_in_gm();
    }

    @Override
    public Double getTotalSliver(){
        return presentationRepo.findTotalSilver_in_gm();
    }

    @Override
    public Double getTotalAmount(){
        return presentationRepo.findTotalAmount();
    }
}
