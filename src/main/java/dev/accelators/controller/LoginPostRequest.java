package dev.accelators.controller;

import jakarta.validation.constraints.NotBlank;

public record LoginPostRequest(
        @NotBlank(message = "The field 'email' is required")
        String email,
        @NotBlank(message = "The field 'password' is required")
        String password
) {}
