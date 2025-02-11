package org.api.events.repo;

import org.api.events.dto.AllCitysDto;
import org.api.events.dto.CityDto;
import org.api.events.models.Relative;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RelativeRepo  extends JpaRepository<Relative, UUID> {

    // write Custom query's
    //@Query("SELECT * FROM relative WHERE firstName = :firstName and lastName = :lastName and city = :city");
    Optional<Relative> findRelativeByFirstNameAndLastNameAndCity(String firstName, String lastName, String city);

    List<String> findByCity(String city);


    // paging and sorting functions

    @Query("SELECT DISTINCT r.city FROM relative r WHERE r.city = :city")
    Page<String> findUniqueCitiesByCity(@Param("city") String city, Pageable pageable);

    @Query("SELECT DISTINCT city FROM relative")
    List<String> findUniqueCitiesByCity();

    Relative findByEmail(String email);

  //  @Query("SELECT new org.api.events.dto.AllCitysDto(r.city) FROM relative  r WHERE r.city = :city")
    @Query("SELECT DISTINCT new org.api.events.dto.AllCitysDto(r.city) FROM relative r")
    List<AllCitysDto> findAllByCity();



}














