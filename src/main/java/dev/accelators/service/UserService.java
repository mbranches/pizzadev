package dev.accelators.service;

import dev.accelators.controller.LoginPostRequest;
import dev.accelators.controller.LoginPostResponse;
import dev.accelators.dto.request.RegisterPostRequest;
import dev.accelators.infra.security.JwtTokenService;
import dev.accelators.model.*;
import dev.accelators.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    public void register(RegisterPostRequest request) {
        Phone phone = Phone.builder().number(request.phone()).build();

        User user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .cpf(request.cpf())
                .phone(phone)
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .active(true)
                .build();

        Role customerRole = roleService.findByNameOrThrowsNotFoundException(RoleName.CUSTOMER);

        UserRole userRole = UserRole.builder().user(user).role(customerRole).build();
        user.setRoles(Collections.singletonList(userRole));

        repository.save(user);
    }

    public LoginPostResponse login(LoginPostRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(request.email(), request.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        User user = (User) authentication.getPrincipal();

        return new LoginPostResponse(jwtTokenService.generateToken(user));
    }
}
