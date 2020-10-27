package com.springboot.assignment.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.assignment.dao.IOrderDao;
import com.springboot.assignment.entities.Customer;
import com.springboot.assignment.entities.CustomerCartDetails;
import com.springboot.assignment.entities.ItemDetails;
import com.springboot.assignment.entities.OrderDetails;
import com.springboot.assignment.entities.OrderItem;
import com.springboot.assignment.model.ItemInfo;
import com.springboot.assignment.model.OrderInfo;
import com.springboot.assignment.service.ICustomerCartService;
import com.springboot.assignment.service.ICustomerService;
import com.springboot.assignment.service.IItemService;
import com.springboot.assignment.service.IOrderService;
import com.springboot.assignment.utils.OrderUtils;

@Service
public class OrderService implements IOrderService {
	
	@Autowired
	IOrderDao iOrderDao;
	
	@Autowired
	ICustomerService customerService;
	
	@Autowired
	IItemService itemService;

	@Autowired
	ICustomerCartService customerCartService;
	
	@Override
	@Transactional
	public OrderDetails getOrderById(int orderId) {
		return iOrderDao.getById(orderId);
	}

	@Override
	@Transactional
	public List<OrderDetails> getCustomerOrders(int customerId) {
		return iOrderDao.getByCustomerId(customerId);
	}

	@Override
	@Transactional
	public OrderDetails saveOrder(OrderDetails order) {
		return iOrderDao.save(order);
	}

	@Override
	@Transactional
	public void deleteOrderById(int orderId) {
		iOrderDao.deleteById(orderId);
	}
	
	/*
	@Override
	@Transactional
	public OrderDetails placeOrder(Customer customer, List<ItemDirectory> items) {
		
		OrderDetails order = new OrderDetails();
		order.setOrderPrice(OrderUtils.getTotalOrderPrice(items));
		order.setExpectedDeliveryDate(OrderUtils.getExpectedDeliveryDate());
		
		
		for(ItemDirectory item: items) {
			if(item.getQuantity() != 0) {
				order.addItem(item, item.getQuantity());
				
				// REMOVE QUANTITY BOUGHT FROM STOCK
				itemService.reCalculateStock(item.getItemId(), item.getQuantity());
			}	
		}
		
		customerService.placeOrder(customer.getCustomerId());
		
		return iOrderDao.save(order);
	}*/
	
	@Override
	@Transactional
	public OrderDetails placeOrder(List<CustomerCartDetails> customerCartDetails) {
		
		
		OrderDetails order = new OrderDetails();
		
		Customer customer = customerService.getCustomerById(customerCartDetails.get(0).getCustomer().getCustomerId());
		
		int orderPrice = 0;
		for(CustomerCartDetails customerCartItem : customerCartDetails) {
			
			if(customerCartItem.getQuantity() != 0) {
				ItemDetails item = itemService.getItemById(customerCartItem.getItem().getItemId());
				
				order.addItem(item, customerCartItem.getQuantity());
				
				// REMOVE QUANTITY BOUGHT FROM STOCK
				itemService.reCalculateStock(item.getItemId(), customerCartItem.getQuantity());
				
				orderPrice += item.getPrice()*customerCartItem.getQuantity();
			}	
		}
		
		order.setExpectedDeliveryDate(OrderUtils.getExpectedDeliveryDate());
		order.setOrderPrice(orderPrice);
		order.setCustomer(customer);
		
		// UPDATE CUSTOMER WITH ORDER DETAILS
		customerService.updateCustomerOrder(customer.getCustomerId());
		
		// CLEAR CART
		customerCartService.removeCustomerCartDetails(customer.getCustomerId());
		
		// PLACE ORDER
		return iOrderDao.save(order);
	}
	
	@Override
	@Transactional
	public OrderInfo getOrderInfo(int orderId) {
		
		
		OrderDetails orderDetails = iOrderDao.getById(orderId);
		OrderInfo orderInfo = new OrderInfo();
		
		orderInfo.setOrderId(orderId);
		orderInfo.setExpectedDeliveryDate(orderDetails.getExpectedDeliveryDate());
		orderInfo.setOrderPrice(orderDetails.getOrderPrice());
		
		List<ItemInfo> itemsInfo = new ArrayList<>();
		for(OrderItem orderItem : orderDetails.getOrderItems()) {
			
			ItemInfo itemInfo = new ItemInfo();
			ItemDetails itemDetails = orderItem.getItem();
			
			itemInfo.setItemName(itemDetails.getItemName());
			itemInfo.setUnitPrice(itemDetails.getPrice());
			itemInfo.setQuantity(orderItem.getQuantity());
			
			itemsInfo.add(itemInfo);
		}
		orderInfo.setItems(itemsInfo);
		
		return orderInfo;
	}

}
