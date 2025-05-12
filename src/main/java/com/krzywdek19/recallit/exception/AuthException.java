package com.krzywdek19.recallit.exception;

import lombok.Getter;

@Getter
public class AuthException extends RuntimeException {
    private final AuthError error;
    public AuthException(AuthError error) {
        super(error.getMessage());
        this.error = error;
    }
}
