package eMarket.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import eMarket.domain.UserInfoLogin;

public class UserInfoLoginValidator implements Validator {
		
	public boolean supports(Class<?> clazz) {
        return UserInfoLogin.class.equals(clazz);
    }

	@Override
	public void validate(Object target, Errors errors) {
		UserInfoLogin dto = (UserInfoLogin) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "", "Field cannot be empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Field cannot be empty.");
		
		if ((dto.getLogin()!=null) && (!dto.getLogin().equals("user"))) {
			errors.rejectValue("login", "", "User not registered.");
		}
		else if ((dto.getPassword() != null) && (!dto.getPassword().equals("password"))) {
			errors.rejectValue("password", "", "Password is not correct.");
		} 
	}
	
	
}
