package com.moneybook.moneybook.exceptions;

public class InvalidTickerException extends RuntimeException{
    public InvalidTickerException() {
        super();
    }

    public InvalidTickerException(String message) {
        super(message);
    }

    public InvalidTickerException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTickerException(Throwable cause) {
        super(cause);
    }

    protected InvalidTickerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
