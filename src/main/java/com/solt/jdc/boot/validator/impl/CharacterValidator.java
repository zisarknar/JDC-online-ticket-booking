package com.solt.jdc.boot.validator.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.solt.jdc.boot.validator.CharacterConstraint;

public class CharacterValidator implements ConstraintValidator<CharacterConstraint, String> {

	@Override
	public void initialize(CharacterConstraint arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String field, ConstraintValidatorContext ctx) {
		return field!=null&&field.matches("[a-z-A-Z ]+");
	}

}
