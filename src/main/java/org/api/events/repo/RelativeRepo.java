package org.api.events.repo;

import org.api.events.models.Relative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RelativeRepo  extends JpaRepository<Relative, Long> {

    // write Custom query's
    // @Query("SELECT * FROM relative WHERE firstName = :firstName and lastName = :lastName and city = :city");
    Optional<Relative> findRelativeByFirstNameAndLastNameAndCity(String firstName, String lastName, String city);
}
