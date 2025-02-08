package org.api.events.service.relativeservice;

import org.api.events.models.Relative;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IRelativeService {
    List<Relative> getAllRelatives();
    Relative saveRelative(Relative relative);
    Optional<Relative> getRelative(String firstName, String lastName, String city);
    Boolean isRelative(Relative relative);
    List<String> getAllUniqueCitys();
}
