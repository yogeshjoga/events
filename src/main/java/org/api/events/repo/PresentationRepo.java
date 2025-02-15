package org.api.events.repo;

import org.api.events.models.Presentation;
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


    List<Presentation> findAll(@Param("userId") UUID userId);

}
