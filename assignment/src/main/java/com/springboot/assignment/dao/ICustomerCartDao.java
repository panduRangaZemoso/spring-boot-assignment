package com.springboot.assignment.dao;

import java.util.List;

import com.springboot.assignment.entities.CustomerCartDetails;

public interface ICustomerCartDao {

	public CustomerCartDetails getByCustomerIdAndItemId(int customerId, int itemId);
	public List<CustomerCartDetails> getByCustomerId(int customerId);
	
	public CustomerCartDetails save(CustomerCartDetails customerCartDetails);
	
	public void deleteByCustomerIdAndItemId(int customerId, int itemId);
	public void deleteByCustomerId(int customerId);
}
