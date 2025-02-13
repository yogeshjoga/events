package org.api.events.repo;

import org.api.events.models.OTP;
import org.api.events.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {

    OTP findByRelativeEmail(String relativeEmail);

    User findByEmail(String email);
}
