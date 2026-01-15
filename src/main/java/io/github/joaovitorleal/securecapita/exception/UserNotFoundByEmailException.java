package io.github.joaovitorleal.securecapita.exception;

public class UserNotFoundByEmailException extends  ApiException {

    public UserNotFoundByEmailException(String message) {
        super(message);
    }
}
