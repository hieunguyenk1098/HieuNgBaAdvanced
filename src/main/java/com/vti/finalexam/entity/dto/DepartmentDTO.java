package com.vti.finalexam.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vti.finalexam.entity.enumtype.DepartmentType;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

    private Long id;

    private String name;

    private Long totalMember;

    private DepartmentType type;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate createdDate;
}
