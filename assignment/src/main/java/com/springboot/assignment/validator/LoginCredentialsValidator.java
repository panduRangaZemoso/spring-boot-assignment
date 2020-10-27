package com.springboot.assignment.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.springboot.assignment.entities.Customer;
import com.springboot.assignment.model.LoginCredentials;
import com.springboot.assignment.service.ICustomerService;
import com.springboot.assignment.utils.PasswordUtil;

@Component
public class LoginCredentialsValidator implements Validator{
	
	@Autowired
	ICustomerService customerService;
	
	@Override
	public boolean supports(Class<?> classObj) {
		return LoginCredentials.class.equals(classObj);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LoginCredentials loginCredentials = (LoginCredentials)target;
		
		Customer customer = customerService.getCustomerByEmail(loginCredentials.getEmail());
		if(customer == null) {
			errors.rejectValue("email", "invalid.email");	
		}
		else if(!PasswordUtil.checkPassword(loginCredentials.getPassword(), customer.getPassword())){
			errors.rejectValue("password", "invalid.password");
		}
	}
	
}
