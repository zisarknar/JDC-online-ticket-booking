package com.solt.jdc.boot.validator.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.solt.jdc.boot.domains.Customer;
import com.solt.jdc.boot.validator.PasswordMatches;


public class PasswordMatchValidator implements ConstraintValidator<PasswordMatches, Object>{

	@Override
	public void initialize(PasswordMatches arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext ctx) {
		Customer customer = (Customer) obj;
        return customer.getTempPassword().equals(customer.getMatchPassword());  
	}

}
