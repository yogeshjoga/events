package org.api.events.exceptions;

public class EmailAlreadyExisted extends RuntimeException {
    public EmailAlreadyExisted(String message) {
        super(message);
    }
}
