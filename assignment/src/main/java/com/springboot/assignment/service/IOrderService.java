package com.springboot.assignment.service;

import java.util.List;

import com.springboot.assignment.entities.CustomerCartDetails;
import com.springboot.assignment.entities.OrderDetails;
import com.springboot.assignment.model.OrderInfo;

public interface IOrderService {

	
	public OrderDetails getOrderById(int orderId);
	public List<OrderDetails> getCustomerOrders(int customerId);
	
	public OrderDetails saveOrder(OrderDetails order);
	public void deleteOrderById(int orderId);
	
	
	
	//public OrderDetails placeOrder(Customer customer, List<ItemDirectory> items);
	public OrderDetails placeOrder(List<CustomerCartDetails> customerCartDetails);
	
	public OrderInfo getOrderInfo(int orderId);
	
}
