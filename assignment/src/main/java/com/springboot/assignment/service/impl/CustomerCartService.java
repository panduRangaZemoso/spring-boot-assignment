package com.springboot.assignment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.assignment.dao.ICustomerCartDao;
import com.springboot.assignment.entities.CustomerCartDetails;
import com.springboot.assignment.service.ICustomerCartService;
import com.springboot.assignment.service.ICustomerService;
import com.springboot.assignment.service.IItemService;

@Service
public class CustomerCartService implements ICustomerCartService{

	@Autowired
	ICustomerService customerService;
	
	@Autowired
	IItemService itemService;
	
	@Autowired
	ICustomerCartDao iCartDao;
	
	@Override
	@Transactional
	public CustomerCartDetails addCustomerCartDetails(int customerId, int itemId) {
		CustomerCartDetails customerCartDetails = new CustomerCartDetails();
		customerCartDetails.setCustomer(customerService.getCustomerById(customerId));
		customerCartDetails.setItem(itemService.getItemById(itemId));
		customerCartDetails.setQuantity(1);
		
		return saveCustomerCartDetails(customerCartDetails);
	}

	
	@Override
	@Transactional
	public CustomerCartDetails updateCustomerCartDetails(int customerId, int itemId, int quantity) {
		
		//Customer customer = customerCartDetails.getCustomer();
		//customer.addCartDetails(customerCartDetails);
		//System.out.println(customer);
		//System.out.println(customer.getCartDetails());
		// customerService.saveCustomer(customer, false); // HERE WE ARE ONLY EDITING CART NOT CUSTOMER DETAILS HENCE FALSE
		
		CustomerCartDetails customerCartDetails = getCustomerCartDetails(customerId, itemId);
		customerCartDetails.setQuantity(quantity);
		
		return saveCustomerCartDetails(customerCartDetails);
	}
	
	@Override
	@Transactional
	public CustomerCartDetails saveCustomerCartDetails(CustomerCartDetails customerCartDetails) {
		
		//Customer customer = customerCartDetails.getCustomer();
		//customer.addCartDetails(customerCartDetails);
		//System.out.println(customer);
		//System.out.println(customer.getCartDetails());
		// customerService.saveCustomer(customer, false); // HERE WE ARE ONLY EDITING CART NOT CUSTOMER DETAILS HENCE FALSE
		
		iCartDao.save(customerCartDetails);
		
		return customerCartDetails;
	}

	
	@Override
	@Transactional
	public CustomerCartDetails getCustomerCartDetails(int customerId, int itemId) {
		
		/*
		Customer customer = customerService.getCustomerById(customerId);
		List<CustomerCartDetails> customerCartDetails = customer.getCartDetails();
		
		
		for(CustomerCartDetails customerCartDetail : customerCartDetails) {
			if(customerCartDetail.getItem().getItemId() == itemId) {
				return customerCartDetail;
			}
		}
		
		return null;
		*/
		
		return iCartDao.getByCustomerIdAndItemId(customerId, itemId);
	}

	
	@Override
	@Transactional
	public List<CustomerCartDetails> getCustomerCartDetails(int customerId) {
		
		/*
		Customer customer = customerService.getCustomerById(customerId);
		List<CustomerCartDetails> customerCartDetails = customer.getCartDetails();
		
		
		for(CustomerCartDetails customerCartDetail : customerCartDetails) {
			if(customerCartDetail.getItem().getItemId() == itemId) {
				return customerCartDetail;
			}
		}
		
		return null;
		*/
		return iCartDao.getByCustomerId(customerId);
	}
		
	
	@Override
	@Transactional
	public void removeCustomerCartDetails(int customerId, int itemId) {
		
		/*
		Customer customer = customerService.getCustomerById(customerId);
		List<CustomerCartDetails> customerCartDetails = customer.getCartDetails();
		
		for(CustomerCartDetails customerCartDetail : customerCartDetails) {
			if(customerCartDetail.getItem().getItemId() == itemId) {
				customerCartDetails.remove(customerCartDetail);
				break;
			}
		}
		
		customerService.saveCustomer(customer, false);
		*/
		
		iCartDao.deleteByCustomerIdAndItemId(customerId, itemId);
	}
	
	
	@Override
	@Transactional
	public void removeCustomerCartDetails(int customerId) {
		
		iCartDao.deleteByCustomerId(customerId);
	}

}
