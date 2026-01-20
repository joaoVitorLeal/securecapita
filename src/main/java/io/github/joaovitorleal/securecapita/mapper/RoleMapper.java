package io.github.joaovitorleal.securecapita.mapper;

import io.github.joaovitorleal.securecapita.domain.Role;
import io.github.joaovitorleal.securecapita.dto.RoleDto;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public RoleDto toDto(Role role) {
        return new RoleDto(role.getName(), role.getPermission());
    }
}
