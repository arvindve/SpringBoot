package com.ai.springboot.validation;

import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy=PasswordMatchValidator.class)
@Documented
public @interface PasswordMatch {

	String message() default "{error.password.mismatch}";

	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};

}
