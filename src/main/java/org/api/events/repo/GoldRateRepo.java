package org.api.events.repo;

import org.api.events.models.GoldRates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GoldRateRepo extends JpaRepository<GoldRates, UUID> {


}
