package com.userrole.user_role_management_backend.user;

import com.userrole.user_role_management_backend.role.Role;
import com.userrole.user_role_management_backend.role.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public UserResponse save(UserRequest request) {
        Set<Role> roles = Optional.ofNullable(request.roles())
                .orElse(Collections.emptySet())
                .stream()
                .map(id -> roleRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Role with id " + id + " does not exist!")))
                .collect(Collectors.toSet());
        User user = User.builder()
                .email(request.email())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .password(request.password())
                .active(false)
                .roles(roles)
                .build();
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public UserResponse update(Long userId, UserRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("No use found with id: " + userId));
        Set<Role> roles = Optional.ofNullable(request.roles())
                .orElse(Collections.emptySet())
                .stream()
                .map(id -> roleRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Role with id " + id + " does not exist!")))
                .collect(Collectors.toSet());
        user.setEmail(request.email());
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setPassword(request.password());
        user.setActive(request.active());
        user.setRoles(roles);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public List<UserResponse> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    public UserResponse findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No use found with id: " + id));
        return userMapper.toUserResponse(user);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public void deleteById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No use found with id: " + id));
        userRepository.deleteById(id);
    }
}
