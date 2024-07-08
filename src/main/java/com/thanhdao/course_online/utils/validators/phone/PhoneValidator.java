package com.thanhdao.course_online.utils.validators.phone;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {
    private String regexp;
    @Override
    public void initialize(Phone constraintAnnotation) {
        regexp = constraintAnnotation.regexp();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // Null values are considered valid
        }
        return value.matches(regexp);
    }
}
