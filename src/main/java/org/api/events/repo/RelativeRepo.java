package org.api.events.repo;

import org.api.events.models.Relative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelativeRepo  extends JpaRepository<Relative, Long> {

    // write Custom query's
}
