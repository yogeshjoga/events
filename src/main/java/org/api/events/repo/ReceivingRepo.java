package org.api.events.repo;

import org.api.events.models.Receiving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceivingRepo extends JpaRepository<Receiving, Long> {

}
