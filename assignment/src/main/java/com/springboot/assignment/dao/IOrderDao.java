package com.springboot.assignment.dao;

import java.util.List;

import com.springboot.assignment.entities.OrderDetails;

public interface IOrderDao {

	public OrderDetails getById(int id);
	public List<OrderDetails> getByCustomerId(int customerId);
	
	public OrderDetails save(OrderDetails orderDetails);
	public void deleteById(int id);
}
