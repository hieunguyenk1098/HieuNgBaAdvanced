package com.vti.finalexam.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    @Override
    public boolean isValid(String pass, ConstraintValidatorContext constraintValidatorContext) {
        if (pass == null) {
            return false;
        }

        if (pass.length() < 6 || pass.length() > 12) {
            return false;
        }

        if (!Pattern.matches(".*[A-Z].*", pass)) {
            return false;
        }

        return true;
    }
}
