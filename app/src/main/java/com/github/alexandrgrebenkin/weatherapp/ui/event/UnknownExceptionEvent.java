package com.github.alexandrgrebenkin.weatherapp.ui.event;

public class UnknownExceptionEvent {
    private Exception exception;

    public UnknownExceptionEvent(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }
}
