package io.github.joaovitorleal.securecapita.exception;

public class UserNotFoundByIdException extends ResourceNotFoundException {

    public UserNotFoundByIdException(Long userId) {
        super("User not found with id: " + userId);
    }
}
