package eMarket.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import eMarket.domain.Product;


public class ProductValidator implements Validator {
		
	public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz);
    }

	@Override
	public void validate(Object target, Errors errors) {
		Product dto = (Product) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Field cannot be empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "", "Field cannot be empty.");
		
		if ((dto.getPrice() != null) && (dto.getPrice() < 0)) {
			errors.rejectValue("price", "", "Price cannot be negative.");
		}
		
	}
	
	
}
