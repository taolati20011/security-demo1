package com.example.securitydemo1.services.impl;

import com.example.securitydemo1.entities.Role;
import com.example.securitydemo1.entities.User;
import com.example.securitydemo1.repository.RoleRepository;
import com.example.securitydemo1.repository.UserRepository;
import com.example.securitydemo1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Iterable<User> displayAllUser() {
        return this.userRepository.displayAllUser();
    }

    @Override
    public void addNewUser(User user) {
        this.userRepository.addNewUser(user.getUser_id(), user.getUsername(), user.getPassword(), user.getEmail());
//        for (GrantedAuthority grantedAuthority: user.getAuthorities()) {
//            Set<Role> roles = new HashSet<>();
//            Role role = this.roleRepository.searchRoleByName(grantedAuthority.getAuthority()).get();
//            if (user.getRoles() == null) {
//                user.setRoles(roles);
//            }
//            user.getRoles().add(role);
//        }
        this.userRepository.save(user);
    }

    @Override
    public Optional<User> searchUserById(Long user_id) {
        return this.userRepository.findById(user_id);
    }

    @Override
    public void deleteUserById(Long user_id) {
        this.userRepository.deleteUserById(user_id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.searchUserByUsername(username).stream().findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("Username is not present"));
        return new User(user.getUser_id(), user.getUsername(), new BCryptPasswordEncoder().encode(user.getPassword()), user.getEmail(), user.getRoles());
    }
}
