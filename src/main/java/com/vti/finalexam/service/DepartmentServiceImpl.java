package com.vti.finalexam.service;

import com.vti.finalexam.Constants.DEPARTMENT;
import com.vti.finalexam.entity.Account;
import com.vti.finalexam.entity.Department;
import com.vti.finalexam.entity.dto.AccountDTO;
import com.vti.finalexam.entity.dto.DepartmentDTO;
import com.vti.finalexam.entity.dto.DepartmentResponseDTO;
import com.vti.finalexam.exception.NotFoundException;
import com.vti.finalexam.repository.DepartmentRepository;
import com.vti.finalexam.service.criteria.DepartmentCriteria;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.vti.finalexam.service.specification.DepartmentSpecification;
import com.vti.finalexam.service.specification.Expression;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final QueryService<Department> queryService;
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentServiceImpl(
            QueryService<Department> queryService,
            DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.queryService = queryService;
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<DepartmentResponseDTO> findAll(Pageable pageable) {
        Page<Department> pageDepartment = departmentRepository.findAll(pageable);
        return new PageImpl<>(
                pageDepartment
                        .getContent()
                        .stream().map(this::toDTO)
                        .collect(Collectors.toList()),
                pageable,
                pageDepartment.getTotalElements());
    }

    @Override
    public Optional<Department> findOne(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Page<DepartmentDTO> findAllByCriteria(DepartmentCriteria departmentCriteria,
                                                 Pageable pageable) {
        Specification<Department> spec = buildSpecification(departmentCriteria);

        Page<Department> page = departmentRepository.findAll(spec, pageable);

        List<DepartmentDTO> list = page.getContent().stream()
                .map(department -> modelMapper.map(department, DepartmentDTO.class)).collect(
                        Collectors.toList());

        return new PageImpl<>(list, pageable, page.getTotalElements());
    }

    @Override
    public List<Department> findByName(String name) {
        return departmentRepository.findAllByName(name);
    }

    @Override
    public List<DepartmentResponseDTO> findByNameToDTO(String name) {
        return findByName(name)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentResponseDTO delete(String name) throws NotFoundException {
        return null;
    }

    @Override
    public Optional<Department> findByNameEquals(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<DepartmentDTO> findOneToDTO(Long id) {
        return findOne(id).map(department -> modelMapper.map(department, DepartmentDTO.class));
    }

    @Override
    public List<DepartmentDTO> search(Expression expression) {
        Specification<Department> spec = buildWhere(expression);
        List<Department> list = departmentRepository.findAll(spec);
        return modelMapper.map(list, new TypeToken<List<AccountDTO>>() {
        }.getType());
    }

    @Override
    public Page<DepartmentDTO> findOneToDTO(Pageable pageable) {
        return null;
    }

    Specification<Department> buildWhere(Expression expression) {
        DepartmentSpecification departmentSpecification = new DepartmentSpecification(expression);
        return Specification.where(departmentSpecification);
    }


    @Override
    public Department create(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    @Transactional
    public Department update(Long id, Department department) throws NotFoundException {
        return findOne(id).map(acc -> {
            department.setId(id);
            return departmentRepository.save(department);
        }).orElseThrow(NotFoundException::new);
    }

    @Override
    public DepartmentResponseDTO delete(Long id) throws NotFoundException {
        return findOne(id).map(dep -> {
            departmentRepository.delete(dep);
            return toDTO(dep);
        }).orElseThrow(NotFoundException::new);
    }

    @Override
    public DepartmentResponseDTO createDepartment(Department department) {
        return null;
    }


    private DepartmentResponseDTO toDTO(Department department) {
        DepartmentResponseDTO depResTO = new DepartmentResponseDTO();

        depResTO.setId(department.getId());
        depResTO.setName(department.getName());
        depResTO.setTotalNumber(department.getTotalMember());
        depResTO.setType(department.getType());
        depResTO.setCreatedDate(department.getCreatedDate());
        return depResTO;
    }

    private Specification<Department> buildSpecification(DepartmentCriteria departmentCriteria) {

        Specification<Department> specification = Specification.where(null);

        if (departmentCriteria.getId()  != null) {
            specification = specification.and(queryService.buildLongFilter(DEPARTMENT.ID, departmentCriteria.getId()));
        }
        if (departmentCriteria.getTotalMember()  != null) {
            specification = specification.and(queryService.buildLongFilter(DEPARTMENT.TOTAL_MEMBER, departmentCriteria.getTotalMember()));
        }
        if (departmentCriteria.getName()  != null) {
            specification = specification.and(queryService.buildStringFilter(DEPARTMENT.NAME, departmentCriteria.getName()));
        }
        if (departmentCriteria.getType()  != null) {
            specification = specification.and(queryService.buildStringFilter(DEPARTMENT.TYPE, departmentCriteria.getType()));
        }

        return specification;
    }
}
