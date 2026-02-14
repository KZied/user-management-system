package com.userrole.user_role_management_backend.role;

import org.springframework.stereotype.Service;

@Service
public class RoleMapper {

    public Role toRole(RoleRequest request) {
        return Role.builder()
                .name(RoleType.valueOf(request.name()))
                .build();
    }

    public RoleResponse toRoleResponse(Role role) {
        return RoleResponse.builder()
                .id(role.getId())
                .name(role.getName().name())
                .build();
    }
}
