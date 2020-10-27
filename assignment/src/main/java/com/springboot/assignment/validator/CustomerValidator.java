package com.springboot.assignment.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.springboot.assignment.entities.Customer;
import com.springboot.assignment.service.ICustomerService;

@Component
public class CustomerValidator implements Validator {

	@Autowired
	ICustomerService customerService;
	
	@Override
	public boolean supports(Class<?> classObj) {
		return Customer.class.equals(classObj);
	}	

	@Override
	public void validate(Object target, Errors errors) {
		Customer customer = (Customer)target;
		
		Customer registeredCustomer = customerService.getCustomerByEmail(customer.getEmail());
		if(registeredCustomer != null) {
			// IF EMAIL EXISTS AND NEW CUSTOMER
			if(customer.getCustomerId() == 0) {
				errors.rejectValue("email", "email.exists");
				if(customer.getPassword() == null || "".equals(customer.getPassword())) {
					errors.rejectValue("password", "password.empty");
				}
				else if(customer.getPassword().length() < 6) {
					errors.rejectValue("password", "password.length.min");
				}
			}
			// IF EMAIL EXISTS AND EXISTING CUSTOMER
			else {
				Customer customerBeforeUpdate = customerService.getCustomerById(customer.getCustomerId());
				if( !customerBeforeUpdate.getEmail().equals( customer.getEmail() )) {
					errors.rejectValue("email", "email.exists");
				}
				if(customer.getPassword() != null && !"".equals(customer.getPassword()) && customer.getPassword().length() < 6) {
					errors.rejectValue("password", "password.length.min");
				}
			}	
		}
		// IF NEW EMAIL IS PROVIDED
		else {
			if(customer.getPassword() == null || "".equals(customer.getPassword())) {
				errors.rejectValue("password", "password.empty");
			}
			else if(customer.getPassword().length() < 6) {
				errors.rejectValue("password", "password.length.min");
			}
		}
		
	}
	
	

}
