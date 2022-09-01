package com.vti.finalexam.validation;
import javax.validation.Constraint;
import java.lang.annotation.Documented;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default "{error.password}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
