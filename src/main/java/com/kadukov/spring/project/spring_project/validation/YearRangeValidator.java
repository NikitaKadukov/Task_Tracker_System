package com.kadukov.spring.project.spring_project.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class YearRangeValidator implements ConstraintValidator<YearRange, LocalDate> {

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        try {
            int year = value.getYear();
            return year >= 1900 && year <= 2100;
        }
        catch (Exception e){
            return false;
        }
    }
}