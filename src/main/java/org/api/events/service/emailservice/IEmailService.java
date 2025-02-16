package org.api.events.service.emailservice;

import org.api.events.constents.VerficationState;
import org.api.events.models.Relative;

import java.util.UUID;

public interface IEmailService {
    Boolean sendVerificationEmail(String email, Relative relative);

    VerficationState verifyOTP(String email, String otp);

    Boolean sendEmail(String to, String subject, String body);
}
