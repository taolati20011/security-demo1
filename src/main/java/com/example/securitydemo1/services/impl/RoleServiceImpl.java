package com.example.securitydemo1.services.impl;

import com.example.securitydemo1.entities.Role;
import com.example.securitydemo1.repository.RoleRepository;
import com.example.securitydemo1.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void addNewRole(Role role) {
        this.roleRepository.addNewRole(role.getRole_id(), role.getRole_name());
    }

    @Override
    public Optional<Role> searchRoleByName(String role_name) {
        return this.roleRepository.searchRoleByName(role_name);
    }
}
