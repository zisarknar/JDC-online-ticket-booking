package com.solt.jdc.boot.validators.customAnnotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.solt.jdc.boot.validators.CharacterValidator;

@Documented
@Constraint(validatedBy=CharacterValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CharacterConstraint {
	String message() default "This field should be character";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
