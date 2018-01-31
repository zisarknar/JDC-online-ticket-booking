package com.solt.jdc.boot.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.solt.jdc.boot.validators.customAnnotations.CharacterConstraint;

public class CharacterValidator implements ConstraintValidator<CharacterConstraint, String> {

    @Override
    public void initialize(CharacterConstraint arg0) {
    }

    @Override
    public boolean isValid(String field, ConstraintValidatorContext ctx) {
        return field != null && field.matches("[a-z-A-Z ]+");
    }

}
