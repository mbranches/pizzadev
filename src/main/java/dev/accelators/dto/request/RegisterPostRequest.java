package dev.accelators.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterPostRequest(
        @NotBlank(message = "The field 'firstName' is required")
        String firstName,
        @NotBlank(message = "The field 'lastName' is required")
        String lastName,
        @NotBlank(message = "The field 'cpf' is required")
        String cpf,
        @NotBlank(message = "The field 'phone' is required")
        String phone,
        @NotBlank(message = "The field 'email' is required")
        @Email(message = "The email is invalid")
        String email,
        @NotBlank(message = "The field 'password' is required")
        String password
) {}
