package ru.itis.vhsroni.exception;

public class CalculatorInvocationException extends RuntimeException{

    public CalculatorInvocationException(Throwable throwable) {
        super("Error during `Swing Calculator` invocation: %s".formatted(throwable.getMessage()), throwable);
    }
}
