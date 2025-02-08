package org.api.events.repo;

import org.api.events.models.Receiving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReceivingRepo extends JpaRepository<Receiving, UUID> {
    @Query("SELECT SUM(r.gold_in_gm) FROM receiving r")
    Double findTotalGold_in_gm();
    @Query("SELECT SUM(r.silver_in_gm) FROM receiving r")
    Double findTotalSilver_in_gm();
    @Query("SELECT SUM(r.amount) FROM receiving r")
    Double findTotalAmount();
}
