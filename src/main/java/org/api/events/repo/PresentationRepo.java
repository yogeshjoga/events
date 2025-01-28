package org.api.events.repo;

import org.api.events.models.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentationRepo extends JpaRepository<Presentation, Long> {
}
