package io.github.joaovitorleal.securecapita.exception;

public class UserNotFoundByEmailException extends ResourceNotFoundException {

    public UserNotFoundByEmailException(String message) {
        super(message);
    }
}
