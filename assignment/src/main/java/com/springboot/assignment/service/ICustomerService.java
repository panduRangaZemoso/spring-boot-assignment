package com.springboot.assignment.service;

import java.util.List;

import com.springboot.assignment.entities.Customer;
import com.springboot.assignment.model.OrderInfo;

public interface ICustomerService {
	
	// CRUD METHODS
	
	public List<Customer> getAllCustomers();
	
	public Customer getCustomerById(int id);
	
	public Customer getCustomerByEmail(String email);
	
	public Customer saveCustomer(Customer customer, boolean encryptPassword);

	public void deleteCustomerById(int customerId);

	
	// APPLICATION METHODS 
	
	public void updateCustomerOrder(int customerId);

	public List<OrderInfo> orders(int customerId);

}
