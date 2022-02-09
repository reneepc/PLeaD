package br.com.opussoftware.plead.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class ProspectTypeValidator implements ConstraintValidator<ProspectType, String> {

    @Override
    public void initialize(ProspectType constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.length() > 2 || s.equals("PF") || s.equals("PJ");
    }
}
