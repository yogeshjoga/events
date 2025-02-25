package org.api.events.service.relativeservice;

import org.api.events.dto.*;
import org.api.events.models.Relative;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IRelativeService {
    List<Relative> getAllRelatives(UUID userId);
    Relative saveRelative(Relative relative);
    Optional<Relative> getRelative(String firstName, String lastName, String city,UUID userId);
    Boolean isRelative(Relative relative,UUID userId);
    List<String> getAllUniqueCitys(UUID userId);
    RelativeResponceDto signUp(SignUpDTO dto);

    List<RelativeByCityPreDto> getRelativeByCityPresenations(String city,UUID userId);
    List<RelativeByCityPreDto> getRelativeByCityReceiving(String city, UUID userId);


    List<AmountFromRelatives> getAmountFromRelativesPresenations(UUID userId);
    List<AmountFromRelatives> getAmountFromRelativesReceiving(UUID userId);

    List<GiftsFromRelatives> getGiftsFromRelativesPresenations(UUID userId);
    List<GiftsFromRelatives> getGiftsFromRelativesReceiving(UUID userId);

    List<GoldFromRelatives> getGoldFromRelativesPresenations(UUID userId);
    List<GoldFromRelatives> getGoldFromRelativesReceiving(UUID userId);

    List<SilverFromRelatives> getSilverFromRelativesPresenations(UUID userId);
    List<SilverFromRelatives> getSilverFromRelativesReceiving(UUID userId);


    Relative getRelativeByFullNameAndCity(String firstName, String lastName, String city, UUID userId);

    // testing
    List<AllCitysDto> getAllCitys();



    // for testing GraphQL implementation
    List<Relative> graphAllRelatives();

}
