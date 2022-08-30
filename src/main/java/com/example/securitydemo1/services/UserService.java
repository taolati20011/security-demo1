package com.example.securitydemo1.services;

import com.example.securitydemo1.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserService {
    public Iterable<User> displayAllUser();

    public void addNewUser(User user);

    public Optional<User> searchUserById(Long user_id);

    public void deleteUserById(Long user_id);

    UserDetails loadUserById(Long userId);
}
