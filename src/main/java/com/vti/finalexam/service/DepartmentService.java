package com.vti.finalexam.service;

import com.vti.finalexam.entity.Department;
import com.vti.finalexam.entity.dto.DepartmentDTO;
import com.vti.finalexam.entity.dto.DepartmentResponseDTO;
import com.vti.finalexam.exception.NotFoundException;
import com.vti.finalexam.service.criteria.DepartmentCriteria;
import java.util.List;
import java.util.Optional;

import com.vti.finalexam.service.specification.Expression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentService {
    Page<DepartmentResponseDTO> findAll(Pageable pageable);

    Optional<Department> findOne(Long id);

    Page<DepartmentDTO> findAllByCriteria(DepartmentCriteria departmentCriteria, Pageable pageable);

    List<Department> findByName(String name);

    Department create(Department department);

    Department update(Long id, Department department) throws NotFoundException;

    DepartmentResponseDTO delete(Long id) throws NotFoundException;

    List<DepartmentResponseDTO> findByNameToDTO(String name);

    DepartmentResponseDTO createDepartment(Department department);

    DepartmentResponseDTO delete(String username) throws NotFoundException;

    Optional<Department> findByNameEquals(String username);

    Optional<DepartmentDTO> findOneToDTO(Long id);

    List<DepartmentDTO> search(Expression expression);

    Page<DepartmentDTO> findOneToDTO(Pageable pageable);
}
