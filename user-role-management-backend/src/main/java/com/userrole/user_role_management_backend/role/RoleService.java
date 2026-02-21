package com.userrole.user_role_management_backend.role;

import com.userrole.user_role_management_backend.exception.InvalidRoleException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleResponse save(RoleRequest request) {

        String roleName = request.name().toUpperCase();

        boolean valid = Arrays.stream(RoleType.values())
                .anyMatch(r -> r.name().equals(roleName));

        if (!valid) {
            throw new InvalidRoleException(
                    "Invalid role. Allowed roles: " + Arrays.toString(RoleType.values())
            );
        }

        RoleType roleType = RoleType.valueOf(roleName);

        if (roleRepository.existsByName(roleType)) {
            throw new InvalidRoleException(
                    roleType.name() + " Role exists already!"
            );
        }

        Role role = Role.builder()
                .name(roleType)
                .build();

        return roleMapper.toRoleResponse(roleRepository.save(role));
    }

    public List<RoleResponse> findAll(){
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(roleMapper::toRoleResponse)
                .toList();
    }

    public RoleResponse findById(Long id){
        return roleRepository.findById(id)
                .map(roleMapper::toRoleResponse)
                .orElseThrow( ()-> new EntityNotFoundException("Role not found with id: " + id));
    }

    public RoleResponse findByName(String name){
        RoleType roleNameFormatted = Arrays.stream(RoleType.values())
                .filter(r -> r.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new InvalidRoleException("Permitted available roles are: " + Arrays.toString(RoleType.values())));
        return roleRepository.findByName(roleNameFormatted)
                .map(roleMapper::toRoleResponse)
                .orElseThrow( ()-> new EntityNotFoundException("Role not found with name: " + name));
    }

    public void deleteAll() {
        roleRepository.deleteAll();
    }

    public void deleteById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + id));
        roleRepository.delete(role);
    }

    public RoleResponse deleteByName(String name){
        RoleType roleNameFormatted = Arrays.stream(RoleType.values())
                .filter(r -> r.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new InvalidRoleException("Permitted available roles are: " + Arrays.toString(RoleType.values())));
        Role role = roleRepository.findByName(roleNameFormatted)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + name));
        roleRepository.delete(role);
        return roleMapper.toRoleResponse(role);
    }
}
