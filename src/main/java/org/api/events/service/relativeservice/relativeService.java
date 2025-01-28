package org.api.events.service.relativeservice;

import org.api.events.models.Relative;
import org.api.events.repo.RelativeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class relativeService implements IRelativeService {

    @Autowired
    private RelativeRepo relativeRepo;

    @Override
    public List<Relative> getAllRelatives() {
        return List.of();
    }

    @Override
    public Relative saveRelative(Relative relative) {
        return relativeRepo.save(relative);
    }
}
