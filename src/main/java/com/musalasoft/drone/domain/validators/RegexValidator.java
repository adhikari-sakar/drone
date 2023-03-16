package com.musalasoft.drone.domain.validators;

import com.musalasoft.drone.domain.contracts.Regex;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RegexValidator implements ConstraintValidator<Regex, String> {

    private Regex annotation;

    @Override
    public void initialize(Regex annotation) {
        this.annotation = annotation;
        ConstraintValidator.super.initialize(annotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return (value != null && value.matches(annotation.regex())) || ((!annotation.required() && value == null));
    }
}
