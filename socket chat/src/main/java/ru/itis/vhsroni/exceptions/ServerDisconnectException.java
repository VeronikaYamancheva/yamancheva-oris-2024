package ru.itis.vhsroni.exceptions;

public class ServerDisconnectException extends RuntimeException{

    public ServerDisconnectException(Throwable cause) {
        super("Exception during server disconnect, cause: %s".formatted(cause.getMessage()));
    }
}
