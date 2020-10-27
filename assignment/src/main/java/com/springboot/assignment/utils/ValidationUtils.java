package com.springboot.assignment.utils;

import org.springframework.validation.BindingResult;

public class ValidationUtils {

	public static boolean checkForErrors(BindingResult bindingResult) {
		
		//System.out.println(bindingResult);
		if(bindingResult.hasErrors()) {
			return true;
		}
		return false;
	}
	
}
