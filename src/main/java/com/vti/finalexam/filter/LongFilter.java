package com.vti.finalexam.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LongFilter extends Filter<Long> {

    private Long greaterThan;

    private Long greaterThanOrEquals;

    private Long lessThan;

    private Long lessThanOrEquals;
}
