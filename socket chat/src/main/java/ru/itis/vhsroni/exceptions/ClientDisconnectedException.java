package ru.itis.vhsroni.exceptions;

public class ClientDisconnectedException extends RuntimeException{

    public ClientDisconnectedException(Throwable cause) {
        super("Client disconnected, cause: %s".formatted(cause.getMessage()));
    }
}
