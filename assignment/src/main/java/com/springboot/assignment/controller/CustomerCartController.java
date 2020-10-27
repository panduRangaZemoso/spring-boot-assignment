package com.springboot.assignment.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.assignment.entities.CustomerCartDetails;
import com.springboot.assignment.service.ICustomerCartService;
import com.springboot.assignment.service.ICustomerService;
import com.springboot.assignment.utils.ValidationUtils;

@Controller
@RequestMapping("/cart")
public class CustomerCartController {
	
	@Autowired
	ICustomerService customerService;
	
	@Autowired
	ICustomerCartService cartService;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	@GetMapping("/add")
	public String addCustomerCartDetails(@RequestParam("customerId") int customerId, @RequestParam("itemId") int itemId,
											Model model, RedirectAttributes redirectAttributes){
		
		if(cartService.getCustomerCartDetails(customerId, itemId) != null) {
			redirectAttributes.addFlashAttribute("message", "Item already in cart, please update the quantity in cart");
		}
		else {
			cartService.addCustomerCartDetails(customerId, itemId);
		}	
		return "redirect:/customer/home?customerId="+customerId;
	}
	
	
	@PostMapping("/save")
	public String saveCustomerCartDetails(@Valid @ModelAttribute("customerCartDetails") CustomerCartDetails customerCartDetails, BindingResult bindingResult){
		
		if(ValidationUtils.checkForErrors(bindingResult)) {
			return "cart/update-quantity";
		}
		
		customerCartDetails = cartService.updateCustomerCartDetails(customerCartDetails.getCustomer().getCustomerId(), customerCartDetails.getItem().getItemId(), customerCartDetails.getQuantity());
		customerCartDetails = cartService.saveCustomerCartDetails(customerCartDetails);
		
		return "redirect:/customer/cart?customerId="+customerCartDetails.getCustomer().getCustomerId();
	}
	
	
	@GetMapping("/update/quantity")
	public String updateCustomerCartDetailsForm(@RequestParam("customerId") int customerId, @RequestParam("itemId") int itemId, Model model){
		
		if(cartService.getCustomerCartDetails(customerId, itemId) == null) {
			//model.addAttribute("error","No such Customer or Item in cart");
			return "error";
		}
		
		model.addAttribute("customerCartDetails",cartService.getCustomerCartDetails(customerId, itemId));
		
		return "cart/update-quantity";
	}
	
	/*
	@PostMapping("/update/quantity")
	public String updateCustomerCartDetailsForm(@ModelAttribute("customerCartDetails") List<CustomerCartDetails> customerCartDetail, Model model){
		
		model.addAttribute("customerCartDetails",cartService.getCustomerCartDetails(customerCartDetail.get(0).getCustomer().getCustomerId(), customerCartDetail.get(0).getItem().getItemId()));
		
		return "cart/update-quantity";
	}
	*/
	
	
	@GetMapping("/delete")
	public String deleteCustomerCartDetails(@RequestParam("customerId") int customerId, @RequestParam("itemId") int itemId){
		
		cartService.removeCustomerCartDetails(customerId, itemId);
		
		return "redirect:/customer/cart?customerId="+customerId;
	}
	
	
}
