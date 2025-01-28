package org.api.events.service.presentationservice;


import org.api.events.models.Presentation;
import org.api.events.repo.PresentationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class PresentationService implements IPresentationService {

    @Autowired
    private PresentationRepo presentationRepo;

    @Override
    public Presentation savePresentation(Presentation presentation) {
        return presentationRepo.save(presentation);
    }
}
