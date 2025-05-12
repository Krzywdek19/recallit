package com.krzywdek19.recallit.exception;

import lombok.Getter;

@Getter
public enum AuthError {
    USER_NOT_FOUND("USER WITH THIS USERNAME NOT FOUND"),
    USERNAME_TAKEN("Username is already taken"),
    EMAIL_TAKEN("Email address is already registered"),
    INVALID_CREDENTIALS("Invalid username or password");


    private final String message;

    AuthError(String message) {
        this.message = message;
    }
}
