package com.pharmalink.exceptions;

public class OutOfStockException extends Exception {
    public OutOfStockException() {
        super("Medicine is out of stock");
    }

    public OutOfStockException(String message) {
        super(message);
    }

    public OutOfStockException(String message, Throwable cause) {9
        super(message, cause);
    }

    public OutOfStockException(Throwable cause) {
        super(cause);
    }
}
