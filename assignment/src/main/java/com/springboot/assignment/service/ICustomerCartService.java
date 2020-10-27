package com.springboot.assignment.service;

import java.util.List;

import com.springboot.assignment.entities.CustomerCartDetails;

public interface ICustomerCartService {

	public CustomerCartDetails addCustomerCartDetails(int customerId, int itemId);
	
	public CustomerCartDetails updateCustomerCartDetails(int customerId, int itemId, int quantity);
	
	public CustomerCartDetails saveCustomerCartDetails(CustomerCartDetails customerCartDetails);
	
	
	public CustomerCartDetails getCustomerCartDetails(int customerId, int itemId);
	
	public List<CustomerCartDetails> getCustomerCartDetails(int customerId);
	
	
	public void removeCustomerCartDetails(int customerId, int itemId);

	public void removeCustomerCartDetails(int customerId);
	
}
