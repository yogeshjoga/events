package org.api.events.service.emailservice;

import org.api.events.constents.VerficationState;

public interface IEmailService {
    Boolean sendVerificationEmail(String email);

    VerficationState verifyOTP(String email, String otp);
}
