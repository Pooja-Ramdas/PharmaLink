package com.pharmalink.exceptions;

public class DatabaseConnectionException extends Exception {
    public DatabaseConnectionException() {
        super("Failed to connect to database");
    }

    public DatabaseConnectionException(String message) {
        super(message);
    }

    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseConnectionException(Throwable cause) {
        super(cause);
    }
}
