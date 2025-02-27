package org.api.events.repo;

import org.api.events.dto.TopFiveRelatives;
import org.api.events.models.Presentation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PresentationRepo extends JpaRepository<Presentation, UUID> {
    @Query("SELECT SUM(p.gold_in_gm) FROM presentations  p")
    Double findTotalGold_in_gm(@Param("userId") UUID userId);
    @Query("SELECT SUM(p.silver_in_gm) FROM presentations p")
    Double findTotalSilver_in_gm(@Param("userId") UUID userId);
    @Query("SELECT SUM(p.amount) FROM presentations p")
    Double findTotalAmount(@Param("userId") UUID userId);


    List<Presentation> findAllByUserId(@Param("userId") UUID userId);


    // Completed Query
    // lets complete all query related things
     @Query("SELECT new org.api.events.dto.TopFiveRelatives(" +
        "CONCAT(r.firstName, ' ', r.lastName), " +
        "p.gold_in_gm) " +
        "FROM relative r " +
        "JOIN r.user u " +
        "JOIN presentations p ON p.user.id = u.id " +
        "WHERE u.id = :userId " +
        "ORDER BY p.gold_in_gm DESC limit 5")
    List<TopFiveRelatives> findTopFiveRelativesByUserIdGold(@Param("userId") UUID userId);


    /**
     *  <b>Pagination for backend pagination</b>
     * @param userId
     * @param pageable
     * @return
     */
    @Query("SELECT new org.api.events.dto.TopFiveRelatives(" +
            "CONCAT(r.firstName, ' ', r.lastName), " +
            "p.gold_in_gm) " +
            "FROM relative r " +
            "JOIN r.user u " +
            "JOIN presentations p ON p.user.id = u.id " +
            "WHERE u.id = :userId " +
            "ORDER BY p.gold_in_gm DESC limit 5")
    List<TopFiveRelatives> findTopFiveRelativesByUserIdGold(@Param("userId") UUID userId, Pageable pageable);

    @Query("SELECT new org.api.events.dto.TopFiveRelatives(" +
            "CONCAT(r.firstName, ' ', r.lastName), " +
            "p.gold_in_gm) " +
            "FROM relative r " +
            "JOIN r.user u " +
            "JOIN presentations p ON p.user.id = u.id " +
            "WHERE u.id = :userId " +
            "ORDER BY p.gold_in_gm DESC limit 5")
    List<TopFiveRelatives> findTopFiveRelativesByUserIdSilver(@Param("userId") UUID userId);

}
