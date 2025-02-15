package org.api.events.repo;

import org.api.events.dto.*;
import org.api.events.models.Receiving;
import org.api.events.models.Relative;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RelativeRepo  extends JpaRepository<Relative, UUID> {

    // write Custom query's
    //@Query("SELECT * FROM relative WHERE firstName = :firstName and lastName = :lastName and city = :city");
    Optional<Relative> findRelativeByFirstNameAndLastNameAndCityAndUserId(String firstName, String lastName, String city,@Param("userId") UUID userId);

    List<String> findByCity(String city,@Param("userId") UUID userId);


    // paging and sorting functions

    @Query("SELECT DISTINCT r.city FROM relative r WHERE r.city = :city")
    Page<String> findUniqueCitiesByCity(@Param("city") String city, @Param("userId") UUID userId, Pageable pageable);

    @Query("SELECT DISTINCT city FROM relative")
    List<String> findUniqueCitiesByCity(@Param("userId") UUID userId);

    Relative findByEmail(String email,@Param("userId") UUID userId);

    List<Receiving> findAllByUserId(UUID userId);


  //  @Query("SELECT new org.api.events.dto.AllCitysDto(r.city) FROM relative  r WHERE r.city = :city")
    @Query("SELECT DISTINCT new org.api.events.dto.AllCitysDto(r.city) FROM relative r")
    List<AllCitysDto> findAllByCity(@Param("userId") UUID userId);



    /**
     * <b>For getting City based on relatives</b>
     * @param city
     * @return
     */
    @Query("SELECT new org.api.events.dto.RelativeByCityPreDto(" +
            "CONCAT(r.firstName, ' ', r.lastName), r.userName, r.email, r.phone, " +
            "p.gold_in_gm, p.silver_in_gm, p.amount, p.objects) " +
            "FROM relative  r " +
            "JOIN r.presentations p " +
            "WHERE r.city = :city")
    List<RelativeByCityPreDto> findByCityPresentations(@Param("city")String city,@Param("userId") UUID userId);


    @Query("SELECT new org.api.events.dto.GiftsFromRelatives(" +
            "CONCAT(r.firstName, ' ', r.lastName), r.email, r.phone, COUNT(p)) " +
            "FROM relative r " +
            "JOIN r.presentations p " +
            "GROUP BY r.firstName, r.lastName, r.email, r.phone")
    List<GiftsFromRelatives> findGiftsFromRelativesPresentations(@Param("userId") UUID userId);


    @Query("SELECT new org.api.events.dto.GoldFromRelatives(" +
            "CONCAT(r.firstName, ' ', r.lastName), r.email, r.phone, SUM(p.gold_in_gm)) " +
            "FROM relative r " +
            "JOIN r.presentations p " +
            "GROUP BY r.firstName, r.lastName, r.email, r.phone")
    List<GoldFromRelatives> findGoldFromRelativesPresentations(@Param("userId") UUID userId);


    @Query("SELECT new org.api.events.dto.GoldFromRelatives(" +
            "CONCAT(r.firstName, ' ', r.lastName), r.email, r.phone, SUM(p.silver_in_gm)) " +
            "FROM relative r " +
            "JOIN r.presentations p " +
            "GROUP BY r.firstName, r.lastName, r.email, r.phone")
    List<SilverFromRelatives> findSilverFromRelativesPresentations(@Param("userId") UUID userId);

    @Query("SELECT new org.api.events.dto.GoldFromRelatives(" +
            "CONCAT(r.firstName, ' ', r.lastName), r.email, r.phone, SUM(p.amount)) " +
            "FROM relative r " +
            "JOIN r.presentations p " +
            "GROUP BY r.firstName, r.lastName, r.email, r.phone")
    List<AmountFromRelatives> findAmountFromRelativesPresentations(@Param("userId") UUID userId);

    // Receiving

    @Query("SELECT new org.api.events.dto.RelativeByCityPreDto(" +
            "CONCAT(r.firstName, ' ', r.lastName), r.userName, r.email, r.phone, " +
            "p.gold_in_gm, p.silver_in_gm, p.amount, p.objects) " +
            "FROM relative  r " +
            "JOIN r.receivings p " +
            "WHERE r.city = :city and r.user.id = :userId")
    List<RelativeByCityPreDto> findByCityReceiving(@Param("city") String city, @Param("userId") UUID userId);


    @Query("SELECT new org.api.events.dto.GiftsFromRelatives(" +
            "CONCAT(r.firstName, ' ', r.lastName), r.email, r.phone, COUNT(p)) " +
            "FROM relative r " +
            "JOIN r.receivings p " +
            "GROUP BY r.firstName, r.lastName, r.email, r.phone")
    List<GiftsFromRelatives> findGiftsFromRelativesReceiving(@Param("userId") UUID userId);


    @Query("SELECT new org.api.events.dto.GoldFromRelatives(" +
            "CONCAT(r.firstName, ' ', r.lastName), r.email, r.phone, SUM(p.gold_in_gm)) " +
            "FROM relative r " +
            "JOIN r.receivings p " +
            "GROUP BY r.firstName, r.lastName, r.email, r.phone")
    List<GoldFromRelatives> findGoldFromRelativesReceiving(@Param("userId") UUID userId);


    @Query("SELECT new org.api.events.dto.GoldFromRelatives(" +
            "CONCAT(r.firstName, ' ', r.lastName), r.email, r.phone, SUM(p.silver_in_gm)) " +
            "FROM relative r " +
            "JOIN r.receivings p " +
            "GROUP BY r.firstName, r.lastName, r.email, r.phone")
    List<SilverFromRelatives> findSilverFromRelativesReceiving(@Param("userId") UUID userId);

    @Query("SELECT new org.api.events.dto.GoldFromRelatives(" +
            "CONCAT(r.firstName, ' ', r.lastName), r.email, r.phone, SUM(p.amount)) " +
            "FROM relative r " +
            "JOIN r.receivings p " +
            "GROUP BY r.firstName, r.lastName, r.email, r.phone")
    List<AmountFromRelatives> findAmountFromRelativesReceiving(@Param("userId") UUID userId);


    /**
     *
     *  user - reg -> dashboard -> querying
     *                              presenation insert
     *                              rece insert
     *                              querying
     *
     *              user -> pre/rec
     *                      user city vizag presentation
     *                       userid and city
     *
     */


}














