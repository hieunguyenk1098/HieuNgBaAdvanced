package com.vti.finalexam.service;

import com.vti.finalexam.Constants.OPERATOR;
import com.vti.finalexam.filter.DateFilter;
import com.vti.finalexam.filter.IntegerFilter;
import com.vti.finalexam.filter.LocalDateFilter;
import com.vti.finalexam.filter.LongFilter;
import com.vti.finalexam.filter.StringFilter;
import com.vti.finalexam.service.specification.CustomSpecification;
import com.vti.finalexam.service.specification.Expression;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class QueryService<T> {

    public Specification<T> buildLongFilter(String field, LongFilter value) {
        if (value.getEquals() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.EQUALS, value.getEquals()));
        }
        if (value.getNotEquals() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.NOT_EQUALS, value.getNotEquals()));
        }
        if (value.getLessThan() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.LESS_THAN, value.getLessThan()));
        }
        if (value.getLessThanOrEquals() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.LESS_THAN_OR_EQUALS, value.getLessThanOrEquals()));
        }
        if (value.getGreaterThan() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.GREATER_THAN, value.getGreaterThan()));
        }
        if (value.getGreaterThanOrEquals() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.GREATER_THAN_OR_EQUALS, value.getGreaterThanOrEquals()));
        }
        return null;
    }

    public Specification<T> buildStringFilter(String field, StringFilter value) {
        if (value.getEquals() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.EQUALS, value.getEquals()));
        }
        if (value.getNotEquals() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.NOT_EQUALS, value.getNotEquals()));
        }
        if (value.getContains() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.CONTAINS, value.getContains()));
        }
        if (value.getNotContains() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.NOT_CONTAINS, value.getNotContains()));
        }
        return null;
    }

    public Specification<T> buildDateFilter(String field, DateFilter value) {
        if (value.getEquals() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.EQUALS, value.getEquals()));
        }
        if (value.getNotEquals() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.NOT_EQUALS, value.getNotEquals()));
        }
        if (value.getLessThan() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.LESS_THAN, value.getLessThan()));
        }
        if (value.getLessThanOrEquals() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.LESS_THAN_OR_EQUALS, value.getLessThanOrEquals()));
        }
        if (value.getGreaterThan() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.GREATER_THAN, value.getGreaterThan()));
        }
        if (value.getGreaterThanOrEquals() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.GREATER_THAN_OR_EQUALS, value.getGreaterThanOrEquals()));
        }
        return null;
    }

}
