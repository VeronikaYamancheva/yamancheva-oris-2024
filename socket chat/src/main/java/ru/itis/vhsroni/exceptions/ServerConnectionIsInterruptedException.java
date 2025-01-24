package ru.itis.vhsroni.exceptions;

public class ServerConnectionIsInterruptedException extends RuntimeException{

    public ServerConnectionIsInterruptedException(Throwable cause) {
        super("Server connection is interrupted. Exception: %s".formatted(cause.getMessage()));
    }
}
