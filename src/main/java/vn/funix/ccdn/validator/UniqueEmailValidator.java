package vn.funix.ccdn.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import vn.funix.ccdn.service.UserService;

public class UniqueEmailValidator
	implements ConstraintValidator<UniqueEmail,String>{
	
	@Autowired
	private UserService userService;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return value!=null && !userService.isEmailAlreadyInUse(value);
		//return false;
		
	}
	
}