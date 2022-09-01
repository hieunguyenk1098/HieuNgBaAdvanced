package com.vti.finalexam.controller;

import com.vti.finalexam.Constants.API;
import com.vti.finalexam.entity.Department;
import com.vti.finalexam.entity.dto.*;
import com.vti.finalexam.exception.NotFoundException;
import com.vti.finalexam.service.DepartmentService;
import com.vti.finalexam.service.DepartmentServiceImpl;
import com.vti.finalexam.service.criteria.DepartmentCriteria;
import com.vti.finalexam.service.specification.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(API.BASE_API_V1)
@Validated
@CrossOrigin("http://localhost:63342")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final MessageSource messageSource;

        @Autowired
        public DepartmentController(DepartmentService departmentService, MessageSource messageSource) {
            this.departmentService = departmentService;
            this.messageSource = messageSource;
        }

//    @GetMapping("")
//    ResponseEntity<Page<DepartmentResponseDTO>> findAll(Pageable pageable) {
//        return ResponseEntity.ok().body(departmentService.findAll(pageable));
//    }

        @GetMapping(API.DEPARTMENTS)
        ResponseEntity<Page<DepartmentDTO>> findAll(DepartmentCriteria departmentCriteria, Pageable pageable) {
            return ResponseEntity.ok().body(departmentService.findAllByCriteria(departmentCriteria, pageable));
        }

//        @GetMapping(API.DEPARTMENTS + "/{id}")
//        ResponseEntity<Optional<Department>> findOne(Long id) {
//            return ResponseEntity.ok().body(departmentService.findOne(id));
//        }

        @GetMapping(API.DEPARTMENTS + "/name")
        ResponseEntity<List<Department>> findByName(
                @PathVariable(name = "value") String name) {
            return ResponseEntity.ok().body(departmentService.findByName(name));
        }

        @PostMapping(API.DEPARTMENTS)
        public ResponseEntity<DepartmentResponseDTO> create(@RequestBody Department department,
                                                            DepartmentServiceImpl departmentServiceImpl){
            try {
                return ResponseEntity.ok().body(departmentServiceImpl.createDepartment(department));
            } catch (NullPointerException e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }

        @DeleteMapping(API.DEPARTMENTS + "/{id}")
        ResponseEntity<DepartmentResponseDTO> deleteByUserId(
                @PathVariable Long id) {
            try {
                return ResponseEntity.ok().body(departmentService.delete(id));
            } catch (NotFoundException e) {
                return ResponseEntity.notFound().build();
            }
        }

        @PostMapping(API.DEPARTMENTS + "/{id}")
        public ResponseEntity<Department> update(@RequestBody Department department,
                                                 @PathVariable Long id, Object departmentServiceImpl){
            try{
                return ResponseEntity.ok().body(departmentService.update(id, department));
            }catch (NotFoundException e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @PutMapping(API.DEPARTMENTS + "/{id}")
        ResponseEntity<Optional<DepartmentDTO>> updateDepartment(@PathVariable Long id,
                                                                 @RequestBody Department departmentFromDTO)
                throws NotFoundException {
            departmentService.update(id, departmentFromDTO);
            return ResponseEntity.ok().body(departmentService.findOneToDTO(id));
        }

    @GetMapping(API.DEPARTMENTS + "/search")
    ResponseEntity<List<DepartmentDTO>> search(
            @RequestParam String field,
            @RequestParam String operator,
            @RequestParam String value) {
        return ResponseEntity.ok()
                .body(departmentService.search(new Expression(field, operator, value)));
    }

    @GetMapping(API.DEPARTMENTS + "/{id}")
    ResponseEntity<Optional<DepartmentDTO>> findOneToDTO(@PathVariable Long id) {
        return ResponseEntity.ok().body(departmentService.findOneToDTO(id));
    }

}

