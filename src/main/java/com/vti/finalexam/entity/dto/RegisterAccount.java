package com.vti.finalexam.entity.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAccount {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
