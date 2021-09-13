package io.bootcamp.BootcampBackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class IncorrectCredentialException extends RuntimeException {
    public IncorrectCredentialException(String message) {
        super(message);
    }
}
