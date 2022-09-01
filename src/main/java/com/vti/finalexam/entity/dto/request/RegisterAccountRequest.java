package com.vti.finalexam.entity.dto.request;

import com.vti.finalexam.validation.EmailNotUnique;
import com.vti.finalexam.validation.Password;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RegisterAccountRequest {
    @NotNull
    @Email
    @EmailNotUnique
    private String email;

    @NotNull
    @Password
    private String password;

    @NotNull
    @NotBlank
    @Length(min = 6, max = 12)
    private String fullName;

    @NotNull
    private String role;

    @NotNull
    private Integer departmentId;

}
