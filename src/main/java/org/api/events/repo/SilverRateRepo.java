package org.api.events.repo;

import org.api.events.models.SilverRates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface SilverRateRepo extends JpaRepository<SilverRates, UUID> {

}
