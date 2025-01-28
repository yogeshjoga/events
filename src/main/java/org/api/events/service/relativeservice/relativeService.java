package org.api.events.service.relativeservice;

import org.api.events.models.Relative;
import org.api.events.repo.RelativeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class relativeService implements IRelativeService {

    private static final Logger log = LoggerFactory.getLogger(relativeService.class);

    @Autowired
    private RelativeRepo relativeRepo;

    @Override
    public List<Relative> getAllRelatives() {
        return relativeRepo.findAll();
    }

    @Override
    public Relative saveRelative(Relative relative) {
        return relativeRepo.save(relative);
    }

    @Override
    public Optional<Relative> getRelative(String firstName, String lastName, String city) {
        log.info("\u001B[1;32m :: Get relative By FirstName LastName City :: \u001B[0m");
       return relativeRepo.findRelativeByFirstNameAndLastNameAndCity(firstName,lastName,city);
    }
}
