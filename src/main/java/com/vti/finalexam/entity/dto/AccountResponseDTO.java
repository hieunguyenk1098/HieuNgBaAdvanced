package com.vti.finalexam.entity.dto;

import com.vti.finalexam.entity.enumtype.DepartmentType;
import com.vti.finalexam.entity.enumtype.Role;
import java.time.LocalDate;
import java.util.Date;
import lombok.Data;

@Data
public class AccountResponseDTO {
    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private Role role;

    private DepartmentInnerDTO departmentInnerDTO;

    public DepartmentInnerDTO getDepartmentInnerDTO() {
        return departmentInnerDTO;
    }

    public void setDepartmentInnerDTO(
        DepartmentInnerDTO departmentInnerDTO) {
        this.departmentInnerDTO = departmentInnerDTO;
    }

    @Data
    public static class DepartmentInnerDTO {
        private Long id;
        private String name;
        private Long totalNumber;
        private DepartmentType type;
        private LocalDate createdDate;

        public DepartmentInnerDTO(Long id, String name, Long totalNumber,
            DepartmentType type, LocalDate createdDate) {
            this.id = id;
            this.name = name;
            this.totalNumber = totalNumber;
            this.type = type;
            this.createdDate = createdDate;
        }

    }
}
