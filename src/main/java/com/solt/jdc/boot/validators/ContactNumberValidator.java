package com.solt.jdc.boot.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.solt.jdc.boot.validators.customAnnotations.ContactNumberConstraint;

public class ContactNumberValidator implements ConstraintValidator<ContactNumberConstraint, String>{

	@Override
	public void initialize(ContactNumberConstraint arg0) {
	}

	@Override
	public boolean isValid(String field, ConstraintValidatorContext ctx) {
		return field!=null&&field.matches("[+0-9]+")&& (field.length() > 8)
				&& (field.length() < 14);
	}

}
