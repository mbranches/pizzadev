package dev.accelators.service;

import dev.accelators.exceptions.NotFoundException;
import dev.accelators.model.Role;
import dev.accelators.model.RoleName;
import dev.accelators.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleRepository repository;

    public Role findByNameOrThrowsNotFoundException(RoleName name) {
        return repository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Role with name '%s' not found".formatted(name)));
    }
}
