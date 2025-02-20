package org.api.events.repo;

import org.api.events.models.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface OTPRepo extends JpaRepository<OTP, UUID> {

  //  OTP findByRelativeEmail(String relativeEmail);

    OTP findByUserEmail(String relativeEmail);

}
