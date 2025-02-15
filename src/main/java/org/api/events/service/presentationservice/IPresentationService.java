package org.api.events.service.presentationservice;


import org.api.events.models.Presentation;

import java.util.List;
import java.util.UUID;

public interface IPresentationService {
    Presentation savePresentation(Presentation presentation);
    List<Presentation> getAll(UUID userId);
    Double getTotalGold(UUID userId);
    Double getTotalAmount(UUID userId);
    Double getTotalSliver(UUID userId);
}
