package com.userrole.user_role_management_backend.role;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("roles")
@RequiredArgsConstructor
@Tag(name = "Role")
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleResponse> save(@RequestBody RoleRequest request) {
        RoleResponse createdRole = roleService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRole);
    }

    @GetMapping
    public ResponseEntity<List<RoleResponse>> findAll(){
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<RoleResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(roleService.findById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<RoleResponse> findByName(@PathVariable String name){
        return ResponseEntity.ok(roleService.findByName(name));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        roleService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        roleService.deleteById(id);
    }

    @DeleteMapping("/name/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByName(@PathVariable String name) {
        roleService.deleteByName(name);
    }
}
