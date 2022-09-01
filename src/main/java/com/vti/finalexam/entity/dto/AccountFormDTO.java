package com.vti.finalexam.entity.dto;

import com.vti.finalexam.entity.enumtype.Role;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountFormDTO {

    @NotBlank(message = "methodArgumentNotValid.notBlank")
    @Length(max = 50, message = "methodArgumentNotValid.length")
    private String username;

    @NotBlank(message = "Không được để trống")
    private String firstName;

    private String lastName;

    private Role role;

    private Long departmentId;
}
