package com.vti.finalexam.entity.dto;

import com.vti.finalexam.entity.dto.AccountResponseDTO.DepartmentInnerDTO;
import com.vti.finalexam.entity.enumtype.DepartmentType;
import com.vti.finalexam.entity.enumtype.Role;

import java.time.LocalDate;
import java.util.Date;
import lombok.Data;

@Data
public class DepartmentResponseDTO {
  private Long id;

  private String name;

  private Long totalNumber;

  private DepartmentType type;;

  private Date createdDate;

    public void setCreatedDate(LocalDate createdDate) {
    }
}
