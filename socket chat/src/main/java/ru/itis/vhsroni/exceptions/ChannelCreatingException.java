package ru.itis.vhsroni.exceptions;

public class ChannelCreatingException extends RuntimeException{

    public ChannelCreatingException(Throwable cause) {
        super("Exception during channel creatingm cause: %s".formatted(cause));
    }
}
