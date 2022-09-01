package com.vti.finalexam.entity.dto;

import com.vti.finalexam.entity.enumtype.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private Long id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private Role role;

    public LoginResponse id(Long id) {
        this.id = id;
        return this;
    }

    public LoginResponse username(String username) {
        this.username = username;
        return this;
    }

    public LoginResponse password(String password) {
        this.password = password;
        return this;
    }

    public LoginResponse firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public LoginResponse lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LoginResponse role(Role role) {
        this.role = role;
        return this;
    }
}
