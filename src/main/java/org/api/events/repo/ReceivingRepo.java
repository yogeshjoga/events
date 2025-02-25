package org.api.events.repo;

import org.api.events.dto.TopFiveRelatives;
import org.api.events.models.Receiving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReceivingRepo extends JpaRepository<Receiving, UUID> {
    @Query("SELECT SUM(r.gold_in_gm) FROM receiving r")
    Double findTotalGold_in_gm(@Param("userId") UUID userId);
    @Query("SELECT SUM(r.silver_in_gm) FROM receiving r")
    Double findTotalSilver_in_gm(@Param("userId") UUID userId);
    @Query("SELECT SUM(r.amount) FROM receiving r")
    Double findTotalAmount(@Param("userId") UUID userId);


    List<Receiving> findAllByUserId(UUID userId);


    /**
     * Get Receiving Silver
     * @param userId
     * @return
     */
    @Query("SELECT new org.api.events.dto.TopFiveRelatives(" +
            "CONCAT(r.firstName, ' ', r.lastName), " +
            "p.silver_in_gm) " +
            "FROM relative r " +
            "JOIN r.receivings p " +
            "ORDER BY p.gold_in_gm DESC limit 5")
    List<TopFiveRelatives> findTopFiveRelativesByUserIdSilver(@Param("userId") UUID userId);


    /**
     * Get Receiving Gold
     * @param userId
     * @return
     */
    @Query("SELECT new org.api.events.dto.TopFiveRelatives(" +
            "CONCAT(r.firstName, ' ', r.lastName), " +
            "p.gold_in_gm) " +
            "FROM relative r " +
            "JOIN r.receivings p " +
            "ORDER BY p.gold_in_gm DESC limit 5")
    List<TopFiveRelatives> findTopFiveRelativesByUserIdGold(@Param("userId") UUID userId);

}
