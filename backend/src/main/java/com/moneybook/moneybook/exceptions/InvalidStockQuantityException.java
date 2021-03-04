package com.moneybook.moneybook.exceptions;

public class InvalidStockQuantityException extends RuntimeException{
    public InvalidStockQuantityException() {
        super();
    }

    public InvalidStockQuantityException(String message) {
        super(message);
    }

    public InvalidStockQuantityException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidStockQuantityException(Throwable cause) {
        super(cause);
    }

    protected InvalidStockQuantityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
