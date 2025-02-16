package org.api.events.controlleradvice;


import org.api.events.exceptions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    /**
     * <b>Set all 404 exceptions in this method</b>
     * @param e
     * @return
     */
    @ExceptionHandler({UserNotFound.class,
            EmailAlreadyExisted.class,
            EmailNotFoundException.class,
            InvalidOTPException.class,
            InvalidOTPException.class,
            OTPExpairedException.class,
            UserNotFoundException.class})
    public ResponseEntity<String> notFound(Exception e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }
}
