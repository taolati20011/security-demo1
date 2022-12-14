package com.example.securitydemo1.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long role_id;

    @Column(name = "role_name")
    private String role_name;

    @ManyToMany(mappedBy = "roles")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<User> users;

    public Role(Long role_id, String role_name) {
        this.role_id = role_id;
        this.role_name = role_name;
    }

    public Role() {}

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
}
