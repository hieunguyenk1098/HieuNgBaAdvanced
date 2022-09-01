package com.vti.finalexam.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailNotUniqueValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailNotUnique {
    String message() default "{error.email.unique}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
