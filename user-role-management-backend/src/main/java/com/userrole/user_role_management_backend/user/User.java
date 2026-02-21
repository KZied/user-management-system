package com.userrole.user_role_management_backend.user;

import com.userrole.user_role_management_backend.role.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private boolean active;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roles;
}
