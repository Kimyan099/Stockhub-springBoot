package com.codecool.stockhub.logger;

public class ExceptionLog {

    private final Exception exception;

    public ExceptionLog(Exception exception) {
        this.exception = exception;
        System.out.println(exception.toString());
    }

    public Exception getException() {
        return exception;
    }
}
