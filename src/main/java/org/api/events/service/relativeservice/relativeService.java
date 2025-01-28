package org.api.events.service.relativeservice;

import org.api.events.models.Relative;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class relativeService implements IRelativeService {

    @Override
    public List<Relative> getAllRelatives() {
        return List.of();
    }
}
