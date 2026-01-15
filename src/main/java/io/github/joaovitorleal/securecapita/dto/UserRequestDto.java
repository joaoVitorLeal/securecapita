package io.github.joaovitorleal.securecapita.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDto(
        @NotBlank(message = "First name must not be empty.")
        String firstName,

        @NotBlank(message = "Last name must not be empty.")
        String lastName,

        @Email(message = "Invalid E-mail.")
        @NotBlank(message = "Email cannot be empty.")
        String email,

        @NotBlank(message = "Password must not be empty.")
        String password
) {
}
