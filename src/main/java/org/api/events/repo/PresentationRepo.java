package org.api.events.repo;

import org.api.events.models.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface PresentationRepo extends JpaRepository<Presentation, UUID> {
    @Query("SELECT SUM(p.gold_in_gm) FROM presentations  p")
    Double findTotalGold_in_gm();
    @Query("SELECT SUM(p.silver_in_gm) FROM presentations p")
    Double findTotalSilver_in_gm();
    @Query("SELECT SUM(p.amount) FROM presentations p")
    Double findTotalAmount();
}
