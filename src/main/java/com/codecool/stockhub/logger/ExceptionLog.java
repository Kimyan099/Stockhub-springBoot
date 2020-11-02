package com.codecool.stockhub.logger;

public class ExceptionLog {

    private  Exception exception;
    private String message;

    public ExceptionLog(String message,Exception exception) {
        this.message = message;
        this.exception = exception;
        System.out.println(exception.toString());
    }

    public ExceptionLog(String message) {
        this.message = message;
    }

    public Exception getException() {
        return exception;
    }

    public String getMessage() {
        return message;
    }
}
