package com.moneybook.moneybook.exceptions;

public class DuplicatedStockException extends RuntimeException{
    public DuplicatedStockException() {
        super();
    }

    public DuplicatedStockException(String message) {
        super(message);
    }

    public DuplicatedStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatedStockException(Throwable cause) {
        super(cause);
    }

    protected DuplicatedStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
