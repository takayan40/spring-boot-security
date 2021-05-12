package com.example.demo.data.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    public User(String name, String password, String email, boolean isAdmin) {
        this.name = name;
        this.password = password;
        this.enabled = true;
        this.email = email;
        this.admin = isAdmin;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private boolean enabled;
    private boolean admin;

}
