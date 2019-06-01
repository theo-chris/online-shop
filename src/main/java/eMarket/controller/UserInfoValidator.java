package eMarket.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import eMarket.domain.UserInfo;

public class UserInfoValidator implements Validator {
		
	public boolean supports(Class<?> clazz) {
        return UserInfo.class.equals(clazz);
    }

	@Override
	public void validate(Object target, Errors errors) {
		UserInfo dto = (UserInfo) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "forenames", "", "Field cannot be empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastnames", "", "Field cannot be empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "", "Field cannot be empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Field cannot be empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password2", "", "Field cannot be empty.");
		
		if ((dto.getPassword() != null) && (dto.getPassword2() != null) && (!dto.getPassword().equals(dto.getPassword2()))) {
			errors.rejectValue("password2", "", "Paswords do not match.");
		}
	}
	
	
}
