package com.vti.finalexam.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vti.finalexam.entity.enumtype.Role;
import java.time.LocalDate;
import lombok.Data;

@Data
public class AccountDTO {
    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private Role role;

    private Long departmentId;

    private String departmentName;

    @JsonProperty("deptTotalMember")
    private Long departmentTotalMember;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate departmentCreatedDate;
}
