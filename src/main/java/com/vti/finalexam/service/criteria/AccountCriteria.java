package com.vti.finalexam.service.criteria;

import com.vti.finalexam.filter.LongFilter;
import com.vti.finalexam.filter.StringFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountCriteria {

    private LongFilter id;

    private StringFilter username;

    private StringFilter password;

    private StringFilter firstName;

    private StringFilter lastName;

    private StringFilter role;

    private StringFilter usernameOrFirstNameOrLastName;
}
