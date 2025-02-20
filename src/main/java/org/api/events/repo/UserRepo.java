package org.api.events.repo;

import org.api.events.models.OTP;
import org.api.events.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {

  //  OTP findByRelativeEmail(String relativeEmail);

    Optional<User> findByEmail(String email);
}
