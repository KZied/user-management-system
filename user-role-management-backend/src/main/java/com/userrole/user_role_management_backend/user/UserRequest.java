package com.userrole.user_role_management_backend.user;

import java.util.Set;

public record UserRequest(
        String email,
        String firstName,
        String lastName,
        String password,
        Boolean active,
        Set <Long> roles
) {
}
