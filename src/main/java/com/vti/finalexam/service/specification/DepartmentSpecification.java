package com.vti.finalexam.service.specification;

import com.vti.finalexam.Constants.DEPARTMENT;
import com.vti.finalexam.Constants.OPERATOR;
import com.vti.finalexam.entity.Department;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class DepartmentSpecification implements Specification<Department> {

    private Expression expression;

    public DepartmentSpecification(java.beans.Expression expression) {
    }

    public DepartmentSpecification(Expression expression) {
    }

    @Override
    public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query,
        CriteriaBuilder criteriaBuilder) {

        if (expression.getField().equals(DEPARTMENT.NAME)) {
            if (expression.getOperator().equals(OPERATOR.CONTAINS)) {
                return criteriaBuilder.like(root.get(expression.getField()), "%" + expression.getValue() + "%");
            }
            if (expression.getOperator().equals(OPERATOR.EQUALS)) {
                return criteriaBuilder.equal(root.get(expression.getField()), expression.getValue());
            }
        }

        if (expression.getField().equals(DEPARTMENT.ID)) {
            if (expression.getOperator().equals(OPERATOR.GREATER_THAN)) {
                return criteriaBuilder.greaterThan(root.get(expression.getField()), Long.valueOf((String) expression.getValue()));
            }
            if (expression.getOperator().equals(OPERATOR.LESS_THAN)) {
                return criteriaBuilder.lessThan(root.get(expression.getField()), Long.valueOf((String) expression.getValue()));
            }
            if (expression.getOperator().equals(OPERATOR.EQUALS)) {
                return criteriaBuilder.equal(root.get(expression.getField()), Long.valueOf((String) expression.getValue()));
            }
        }
        return null;
    }


}
