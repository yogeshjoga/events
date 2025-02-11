package org.api.events.service.relativeservice;

import org.api.events.dto.AllCitysDto;
import org.api.events.dto.RelativeResponceDto;
import org.api.events.dto.SignUpDTO;
import org.api.events.models.Relative;
import java.util.List;
import java.util.Optional;

public interface IRelativeService {
    List<Relative> getAllRelatives();
    Relative saveRelative(Relative relative);
    Optional<Relative> getRelative(String firstName, String lastName, String city);
    Boolean isRelative(Relative relative);
    List<String> getAllUniqueCitys();
    RelativeResponceDto signUp(SignUpDTO dto);

    // testing
    List<AllCitysDto> getAllCitys();
}
