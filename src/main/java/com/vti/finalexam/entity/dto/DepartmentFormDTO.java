package com.vti.finalexam.entity.dto;

import com.vti.finalexam.entity.enumtype.DepartmentType;
import com.vti.finalexam.entity.enumtype.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentFormDTO {

    @NotBlank(message = "methodArgumentNotValid.notBlank")
    @Length(max = 50, message = "methodArgumentNotValid.length")
    private String name;

    @NotBlank(message = "Không được để trống")
    private Long totalMember;

    private DepartmentType type;
}
