package com.springboot.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.assignment.entities.CustomerCartDetails;
import com.springboot.assignment.entities.OrderDetails;
import com.springboot.assignment.service.ICustomerCartService;
import com.springboot.assignment.service.ICustomerService;
import com.springboot.assignment.service.IOrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	IOrderService orderService;

	@Autowired
	ICustomerCartService cartService;
	
	@Autowired
	ICustomerService customerService;

	/*
	@PostMapping("/place")
	public String placeOrder(@ModelAttribute("customerCartDetails") ArrayList<CustomerCartDetails> customerCartDetails, Model model) {
		
		System.out.println(" ----> "+customerCartDetails);
		
		OrderDetails order = orderService.placeOrder(customerCartDetails);
		
		model.addAttribute("order",order);
		return "order/order-success";
	}
	*/
	
	@GetMapping("/place")
	public String placeOrder(@RequestParam("customerId") String customerId, Model model) {
		
		List<CustomerCartDetails> customerCartDetails = cartService.getCustomerCartDetails(Integer.parseInt(customerId));
		OrderDetails order = orderService.placeOrder(customerCartDetails);
		
		model.addAttribute("order",order);
		return "order/order-success";
	}
	
	/*
	@PostMapping("/place")
	public String placeOrder(@ModelAttribute("customer") Customer customer, Model model) {
		
		System.out.println(" ----> "+customer);
		
		List<CustomerCartDetails> customerCartDetails = cartService.getCustomerCartDetails(customer.getCustomerId());
		OrderDetails order = orderService.placeOrder(customerCartDetails);
		
		model.addAttribute("order",order);
		return "order/order-success";
	}*/
}
