package dev.accelators.service;

import dev.accelators.dto.request.RegisterPostRequest;
import dev.accelators.model.*;
import dev.accelators.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final RoleService roleService;

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
}
