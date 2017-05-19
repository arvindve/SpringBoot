package com.ai.springboot.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ai.springboot.model.SiteUser;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, SiteUser> {

	@Override
	public void initialize(PasswordMatch p) {

	}

	@Override
	public boolean isValid(SiteUser user, ConstraintValidatorContext c) {

		String painPassword = user.getPlainPassword();
		String repeatPassword = user.getRepeatPassword();

		if (painPassword == null || painPassword.length() == 0) {
			return true;
		}

		if (painPassword == null || !painPassword.equals(repeatPassword)) {
			return false;
		}

		return true;

	}

}
