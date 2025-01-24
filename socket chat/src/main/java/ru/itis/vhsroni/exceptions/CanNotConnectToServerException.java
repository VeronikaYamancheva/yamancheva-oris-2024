package ru.itis.vhsroni.exceptions;

public class CanNotConnectToServerException extends RuntimeException {

    public CanNotConnectToServerException(Throwable cause) {
        super("Can't connect to server because of: %s".formatted(cause.getMessage()));
    }
}
