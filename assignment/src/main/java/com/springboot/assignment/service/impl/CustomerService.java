package com.springboot.assignment.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.assignment.dao.ICustomerDao;
import com.springboot.assignment.entities.Customer;
import com.springboot.assignment.entities.CustomerAdditionalDetails;
import com.springboot.assignment.entities.OrderDetails;
import com.springboot.assignment.model.OrderInfo;
import com.springboot.assignment.service.ICustomerService;
import com.springboot.assignment.service.IOrderService;
import com.springboot.assignment.utils.PasswordUtil;

@Service
public class CustomerService implements ICustomerService {

	@Autowired
	//@Qualifier("customerDao")
	ICustomerDao customerDao;

	@Autowired
	IOrderService orderService;
	
	@Override
	@Transactional
	public List<Customer> getAllCustomers() {
		return customerDao.getAll();
	}
	
	
	@Override
	@Transactional
	public Customer getCustomerById(int id) {
		return customerDao.getById(id);
	}
	
	@Override
	@Transactional
	public Customer getCustomerByEmail(String email) {
		return customerDao.getByEmail(email);
	}
	
	
	@Override
	@Transactional
	public Customer saveCustomer(Customer customer, boolean encryptPassword) {
		if(encryptPassword) {
			customer.setPassword(PasswordUtil.encryptPassword(customer.getPassword()));
		}
		return customerDao.save(customer);
	}


	@Override
	@Transactional
	public void deleteCustomerById(int customerId) {
		customerDao.deleteById(customerId);
	}


	
	
	@Override
	@Transactional
	public void updateCustomerOrder(int customerId) {
		
		Customer customer = customerDao.getById(customerId);
		
		CustomerAdditionalDetails customerAdditionalDetails = 
							customer.getCustomerAdditionalDetails();
		if(customerAdditionalDetails == null) {
			customerAdditionalDetails = new CustomerAdditionalDetails();
		}
		
		customerAdditionalDetails.setLastOrderDate(new Date());
		customerAdditionalDetails.setNumberOfOrders(customerAdditionalDetails.getNumberOfOrders() + 1);
		customerAdditionalDetails.setCustomer(customer);
		customer.setCustomerAdditionalDetails(customerAdditionalDetails);
		
		customerDao.save(customer);
	}


	@Override
	@Transactional
	public List<OrderInfo> orders(int customerId) {
		
		Customer customer = customerDao.getById(customerId);
		
		List<OrderInfo> ordersInfo = new ArrayList<>();
		for(OrderDetails order : customer.getOrders()) {
			ordersInfo.add( orderService.getOrderInfo(order.getOrderId()) );
		}
		
		return ordersInfo;
	}

	
}
