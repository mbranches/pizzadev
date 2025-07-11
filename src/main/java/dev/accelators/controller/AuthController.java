package dev.accelators.controller;

import dev.accelators.dto.request.RegisterPostRequest;
import dev.accelators.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {
    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterPostRequest request) {
        service.register(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginPostResponse> login(@Valid @RequestBody LoginPostRequest request) {
        LoginPostResponse response = service.login(request);

        return ResponseEntity.ok(response);
    }
}
