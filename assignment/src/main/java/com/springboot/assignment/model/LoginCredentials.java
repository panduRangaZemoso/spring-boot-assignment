package com.springboot.assignment.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class LoginCredentials {

	@NotNull(message="Please enter a valid email")
	@Email(message="Please enter a valid email")
	private String email;
	
	@NotNull(message="Please enter a valid password")
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	@Override
	public String toString() {
		return "LoginCredentials [email=" + email + ", password=" + password + "]";
	}
}
