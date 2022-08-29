package com.example.securitydemo1.controller;

import com.example.securitydemo1.entities.Role;
import com.example.securitydemo1.entities.User;
import com.example.securitydemo1.services.impl.RoleServiceImpl;
import com.example.securitydemo1.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Iterable<User>> getAllUser() {
        return ResponseEntity.ok(userService.displayAllUser());
    }

    @GetMapping("/get-by-id/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<User> findUserById(@PathVariable("id") Long user_id) {
        //this.userService.searchUserById(user_id).get().getRoles().forEach((r) -> System.out.println(r.getRole_name()));
        return ResponseEntity.ok(userService.searchUserById(user_id).get());
    }

    @PostMapping("/add")
    @PreAuthorize("ADMIN")
    public ResponseEntity<?> addNewUser(@RequestBody User user) {
        //user.getRoles().forEach((r) -> System.out.println(r.getRole_name()));
        this.userService.addNewUser(user);
        return ResponseEntity.ok("New user created");
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("ADMIN")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long user_id) {
        this.userService.deleteUserById(user_id);
        return ResponseEntity.ok("User has id = " + user_id + " deleted!");
    }
}
