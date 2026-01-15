package io.github.joaovitorleal.securecapita.exception;

public abstract class NotificationFailureException extends ApiException {

    public NotificationFailureException(String message) {
        super(message);
    }

    public NotificationFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
