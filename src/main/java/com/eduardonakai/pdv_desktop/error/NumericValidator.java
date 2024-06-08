package com.eduardonakai.pdv_desktop.error;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NumericValidator implements ConstraintValidator<Numeric, String> {

    @Override
    public void initialize(Numeric constraintAnnotation) {
        // Custom initialization logic, if necessary
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Consider null as valid, use @NotNull for null checks
        }
        return value.matches("\\d+");
    }
}