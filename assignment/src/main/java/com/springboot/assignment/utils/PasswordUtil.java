package com.springboot.assignment.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordUtil {
	
	public static String encryptPassword(String plainTextPassword) {
		
		return "{bcrypt}"+BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
		
		//return plainTextPassword;
	}
	
	public static boolean checkPassword(String inputPassword, String encryptedDbPassword){
		
		return BCrypt.checkpw(inputPassword, encryptedDbPassword.substring(8));
		
		//return encryptedDbPassword.equals(inputPassword);
	}	

}
