package com.springboot.assignment.dao;

import java.util.List;

import com.springboot.assignment.entities.Customer;

public interface ICustomerDao {

	public List<Customer> getAll();
	public Customer getById(int id);
	public Customer getByEmail(String email);
	
	public Customer save(Customer customer);
	public void deleteById(int id);
	
}
