package io.github.joaovitorleal.securecapita.exception;

public class UserNotFoundByIdException extends  RuntimeException {
    public UserNotFoundByIdException(String message) {
        super(message);
    }
}
