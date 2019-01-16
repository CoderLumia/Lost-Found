package com.lumia.lostfound.exception;

public class ErrorArgumentException extends RuntimeException{

    public ErrorArgumentException() {
    }

    public ErrorArgumentException(String message) {
        super(message);
    }

    public ErrorArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorArgumentException(Throwable cause) {
        super(cause);
    }

    public ErrorArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
