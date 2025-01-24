package ru.itis.vhsroni.exceptions;

public class PortIsInUseException extends RuntimeException{

    public PortIsInUseException(int port) {
        super("Port `%s` already in use.".formatted(port));
    }
}
