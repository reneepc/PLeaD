package br.com.opussoftware.plead.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProspectTypeValidator implements ConstraintValidator<ProspectType, String> {

    @Override
    public void initialize(ProspectType constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s != null) {
            return s.equals("PF") || s.equals("PJ");
        }
        return false;
    }
}
