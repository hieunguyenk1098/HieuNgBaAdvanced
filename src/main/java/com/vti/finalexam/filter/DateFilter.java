package com.vti.finalexam.filter;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@NoArgsConstructor
public class DateFilter extends Filter<Date> {

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date greaterThan;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date greaterThanOrEquals;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date lessThan;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date lessThanOrEquals;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    public Date getEquals() {
        return equals;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd")
    public void setEquals(Date equals) {
        this.equals = equals;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd")
    public Date getNotEquals() {
        return notEquals;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd")
    public void setNotEquals(Date notEquals) {
        this.notEquals = notEquals;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd")
    public Date getGreaterThan() {
        return greaterThan;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd")
    public void setGreaterThan(Date greaterThan) {
        this.greaterThan = greaterThan;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd")
    public Date getGreaterThanOrEquals() {
        return greaterThanOrEquals;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd")
    public void setGreaterThanOrEquals(Date greaterThanOrEquals) {
        this.greaterThanOrEquals = greaterThanOrEquals;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd")
    public Date getLessThan() {
        return lessThan;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd")
    public void setLessThan(Date lessThan) {
        this.lessThan = lessThan;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd")
    public Date getLessThanOrEquals() {
        return lessThanOrEquals;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd")
    public void setLessThanOrEquals(Date lessThanOrEquals) {
        this.lessThanOrEquals = lessThanOrEquals;
    }
}
