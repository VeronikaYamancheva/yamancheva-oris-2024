package ru.itis.vhsroni.exceptions;

public class ClientConnectionException extends RuntimeException{

    public ClientConnectionException(Throwable cause) {
        super("Error during client connection, cause: %s".formatted(cause.getMessage()));
    }
}
