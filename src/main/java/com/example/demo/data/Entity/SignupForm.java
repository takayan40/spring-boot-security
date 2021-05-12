package com.example.demo.data.Entity;

import lombok.Data;

@Data
public class SignupForm {
    private String name;
    private String email;
    private String password;
    private String[] roles;
}
