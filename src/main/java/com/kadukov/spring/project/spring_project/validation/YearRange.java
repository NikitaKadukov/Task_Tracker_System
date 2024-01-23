package com.kadukov.spring.project.spring_project.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = YearRangeValidator.class)
@Documented
public @interface YearRange {
    String message() default "Год должен быть от 1900 до 2100";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
