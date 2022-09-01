package com.vti.finalexam.service.criteria;

import com.vti.finalexam.filter.DateFilter;
import com.vti.finalexam.filter.LocalDateFilter;
import com.vti.finalexam.filter.LongFilter;
import com.vti.finalexam.filter.StringFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentCriteria {

    private LongFilter id;

    private StringFilter name;

    private LongFilter totalMember;

    private StringFilter type;

    private LocalDateFilter createdDate;
}
