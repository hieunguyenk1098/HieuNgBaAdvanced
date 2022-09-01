package com.vti.finalexam.filter;

import java.time.LocalDate;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@NoArgsConstructor
public class LocalDateFilter extends Filter<LocalDate> {

    @DateTimeFormat(pattern="dd-MM-yyyy")
    private LocalDate greaterThan;

    @DateTimeFormat(pattern="dd-MM-yyyy")
    private LocalDate greaterThanOrEquals;

    @DateTimeFormat(pattern="dd-MM-yyyy")
    private LocalDate lessThan;

    @DateTimeFormat(pattern="dd-MM-yyyy")
    private LocalDate lessThanOrEquals;

    @DateTimeFormat(pattern="dd-MM-yyyy")
    public LocalDate getEquals() {
        return equals;
    }

    @DateTimeFormat(pattern="dd-MM-yyyy")
    public void setEquals(LocalDate equals) {
        this.equals = equals;
    }

    @DateTimeFormat(pattern="dd-MM-yyyy")
    public LocalDate getNotEquals() {
        return notEquals;
    }

    @DateTimeFormat(pattern="dd-MM-yyyy")
    public void setNotEquals(LocalDate notEquals) {
        this.notEquals = notEquals;
    }

    @DateTimeFormat(pattern="dd-MM-yyyy")
    public LocalDate getGreaterThan() {
        return greaterThan;
    }

    @DateTimeFormat(pattern="dd-MM-yyyy")
    public void setGreaterThan(LocalDate greaterThan) {
        this.greaterThan = greaterThan;
    }

    @DateTimeFormat(pattern="dd-MM-yyyy")
    public LocalDate getGreaterThanOrEquals() {
        return greaterThanOrEquals;
    }

    @DateTimeFormat(pattern="dd-MM-yyyy")
    public void setGreaterThanOrEquals(LocalDate greaterThanOrEquals) {
        this.greaterThanOrEquals = greaterThanOrEquals;
    }

    @DateTimeFormat(pattern="dd-MM-yyyy")
    public LocalDate getLessThan() {
        return lessThan;
    }

    @DateTimeFormat(pattern="dd-MM-yyyy")
    public void setLessThan(LocalDate lessThan) {
        this.lessThan = lessThan;
    }

    @DateTimeFormat(pattern="dd-MM-yyyy")
    public LocalDate getLessThanOrEquals() {
        return lessThanOrEquals;
    }

    @DateTimeFormat(pattern="dd-MM-yyyy")
    public void setLessThanOrEquals(LocalDate lessThanOrEquals) {
        this.lessThanOrEquals = lessThanOrEquals;
    }
}
