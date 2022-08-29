package com.example.securitydemo1.controller;

import com.example.securitydemo1.entities.Role;
import com.example.securitydemo1.services.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class RoleController {
    @Autowired
    private final RoleServiceImpl roleService;

    public RoleController(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/add-role")
    //@PreAuthorize("ADMIN")
    public ResponseEntity<?> addNewRole(@RequestBody Role role) {
        this.roleService.addNewRole(role);
        return ResponseEntity.ok("New role added");
    }

    @GetMapping("/search-role/{name}")
    //@PreAuthorize("ADMIN")
    public ResponseEntity<Optional<Role>> searchRoleByName(@PathVariable("name") String role_name) {
        return ResponseEntity.ok(this.roleService.searchRoleByName(role_name));
    }
}
