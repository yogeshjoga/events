package org.api.events.service.relativeservice;

import org.api.events.dto.*;
import org.api.events.models.Relative;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IRelativeService {
    List<Relative> getAllRelatives();
    Relative saveRelative(Relative relative);
    Optional<Relative> getRelative(String firstName, String lastName, String city);
    Boolean isRelative(Relative relative);
    List<String> getAllUniqueCitys();
    RelativeResponceDto signUp(SignUpDTO dto);

    List<RelativeByCityPreDto> getRelativeByCityPresenations(String city);
    List<RelativeByCityPreDto> getRelativeByCityReceiving(String city, UUID userId);


    List<AmountFromRelatives> getAmountFromRelativesPresenations();
    List<AmountFromRelatives> getAmountFromRelativesReceiving();

    List<GiftsFromRelatives> getGiftsFromRelativesPresenations();
    List<GiftsFromRelatives> getGiftsFromRelativesReceiving();

    List<GoldFromRelatives> getGoldFromRelativesPresenations();
    List<GoldFromRelatives> getGoldFromRelativesReceiving();

    List<SilverFromRelatives> getSilverFromRelativesPresenations();
    List<SilverFromRelatives> getSilverFromRelativesReceiving();






    // testing
    List<AllCitysDto> getAllCitys();

}
