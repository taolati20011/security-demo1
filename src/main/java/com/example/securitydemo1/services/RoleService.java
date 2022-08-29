package com.example.securitydemo1.services;

import com.example.securitydemo1.entities.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleService {
    public void addNewRole(Role role);

    public Optional<Role> searchRoleByName(String role_name);
}
