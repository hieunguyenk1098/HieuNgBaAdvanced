package com.vti.finalexam.filter;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Filter <FIELD_TYPE> implements Serializable {

    protected FIELD_TYPE equals;

    protected FIELD_TYPE notEquals;
}