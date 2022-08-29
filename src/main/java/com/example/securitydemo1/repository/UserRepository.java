package com.example.securitydemo1.repository;

import com.example.securitydemo1.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u")
    public Iterable<User> displayAllUser();

    @Modifying
    @Query(value = "insert into User(user_id, username, password, email) value(?, ?, ?, ?)", nativeQuery = true)
    public void addNewUser(Long user_id, String username, String password, String email);

    @Query("SELECT u FROM User u WHERE u.user_id = ?1")
    public Optional<User> searchUserById(Long user_id);

    @Modifying
    @Query(value = "DELETE FROM User u WHERE u.user_id = ?1", nativeQuery = true)
    public void deleteUserById(Long user_id);

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    public Optional<User> searchUserByUsername(String username);
}
