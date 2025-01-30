package org.api.events.service.presentationservice;


import org.api.events.models.Presentation;

import java.util.List;

public interface IPresentationService {
    Presentation savePresentation(Presentation presentation);
    List<Presentation> getAll();
}
