package org.api.events.service.emailservice;

import org.api.events.constents.VerficationState;

public interface IEmialService {
    Boolean sendVerificationEmail(String email);

    VerficationState verifyOTP(String email, String otp);
}
