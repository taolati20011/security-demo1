package com.example.securitydemo1.repository;

import com.example.securitydemo1.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Modifying
    @Query(value = "insert into Role(role_id, role_name) value(?, ?)", nativeQuery = true)
    public void addNewRole(Long role_id, String role_name);

    @Query("SELECT r FROM Role r WHERE r.role_name = ?1")
    public Optional<Role> searchRoleByName(String role_name);
}
