package com.application.jpa.exceptions;

public abstract class BaseException extends Exception {
    public BaseException(String message) {
        super(message);
    }
}
