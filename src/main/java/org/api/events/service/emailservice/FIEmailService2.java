package org.api.events.service.emailservice;


import org.api.events.models.User;

@FunctionalInterface
public interface FIEmailService2 {
    Boolean sendVerificationEmail(String email, User user);
}
