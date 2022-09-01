package com.vti.finalexam.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
    private Integer id;
    private String email;
    private  String password;
    private String fullName;

    private String role;

    private Integer departmentId;

    private LocalDateTime createdDate;

}
