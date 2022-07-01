package com.leanpay.loancalculator.exceptions;

public class IncompleteDataException extends RuntimeException{

    public IncompleteDataException() {
        super();
    }

    public IncompleteDataException(String message) {
        super(message);
    }

    public IncompleteDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncompleteDataException(Throwable cause) {
        super(cause);
    }

    protected IncompleteDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
