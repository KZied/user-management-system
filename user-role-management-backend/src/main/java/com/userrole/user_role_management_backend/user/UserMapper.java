package com.userrole.user_role_management_backend.user;

import com.userrole.user_role_management_backend.role.Role;
import com.userrole.user_role_management_backend.role.RoleType;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    public User toUser(UserRequest request, Set<Role> roles) {
        return User.builder()
                .email(request.email())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .password(request.password())
                .active(request.active())
                .roles(roles)
                .build();
    }

    public UserResponse toUserResponse(User user) {
        Set<RoleType> roleTypes = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .active(user.isActive())
                .roles(roleTypes)
                .build();
    }
}
