package com.springboot.assignment.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.springboot.assignment.entities.Customer;
import com.springboot.assignment.entities.CustomerAdditionalDetails;
import com.springboot.assignment.entities.ItemDetails;
import com.springboot.assignment.model.LoginCredentials;
import com.springboot.assignment.model.OrderInfo;
import com.springboot.assignment.service.ICustomerCartService;
import com.springboot.assignment.service.ICustomerService;
import com.springboot.assignment.service.IItemService;
import com.springboot.assignment.utils.PasswordUtil;
import com.springboot.assignment.utils.ValidationUtils;


@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	ICustomerService customerService;
	
	@Autowired
	IItemService itemService;
	
	@Autowired
	ICustomerCartService cartService;
	
	@Autowired
	@Qualifier("customerValidator")
	Validator customerCreationValidator;
	
	@Autowired
	@Qualifier("loginCredentialsValidator")
	Validator loginValidator;
	
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	
	@GetMapping("/login/form")
	public String customerLoginForm(Model model){
		model.addAttribute("credentials", new LoginCredentials());
		return "customer/login-customer";
	}
	
	@PostMapping("/login")
	public String customerLogin(@Valid @ModelAttribute("credentials") LoginCredentials loginCredentials, BindingResult bindingResult
										,Model model, RedirectAttributes attributes){
		
		// VALIDATIONS
		if(ValidationUtils.checkForErrors(bindingResult)) {
			return "customer/login-customer";
		}
		
		loginValidator.validate(loginCredentials, bindingResult);
		
		if(ValidationUtils.checkForErrors(bindingResult)) {
			return "customer/login-customer";
		}
		
		// PROCESSING
		Customer customer = customerService.getCustomerByEmail(loginCredentials.getEmail());
		if(PasswordUtil.checkPassword(loginCredentials.getPassword(), customer.getPassword())) {
			//attributes.addAttribute("id", customer.getCustomerId());
			return "redirect:/customer/home?customerId="+customer.getCustomerId();
		}
		
		return "redirect:/customer/login/form";
	}
	
	
	@GetMapping("/home")
	public String customerHome(@RequestParam("customerId") String customerId, HttpServletRequest request, Model model){
		
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		if(inputFlashMap!=null) {
			model.addAttribute("message", inputFlashMap.get("message"));
		}
		
		Customer customer = customerService.getCustomerById(Integer.parseInt(customerId));
		List<ItemDetails> itemDetails = new ArrayList<>();
		for(ItemDetails item : itemService.getAllAvailableItems()) {
			itemDetails.add(item);
		}
		
		if(customer == null || itemDetails==null) {
			return "error";
		}
		
		model.addAttribute("customer",customer);
		model.addAttribute("items",itemDetails);
		
		return "customer/customer-home";
	}
	
	
	@GetMapping("/cart")
	public String customerCart(@RequestParam("customerId") String customerId, Model model){
		
		Customer customer = customerService.getCustomerById(Integer.parseInt(customerId));
		
		model.addAttribute("customer",customer);
		model.addAttribute("customerCartDetails", cartService.getCustomerCartDetails(Integer.parseInt(customerId)));
		
		return "customer/customer-cart";
	}
	
	
	@GetMapping("/orders")
	public String customerOrders(@RequestParam("customerId") String customerId, Model model) {	
		
		Customer customer = customerService.getCustomerById(Integer.parseInt(customerId));
		List<OrderInfo> orderInfo = customerService.orders(Integer.parseInt(customerId));
		
		model.addAttribute("customer",customer);
		model.addAttribute("orders",orderInfo);	
		return "customer/customer-order";
	}
	
	
	@GetMapping("/save/form")
	public String addCustomerForm(Model model){
		Customer customer = new Customer();
		customer.setCustomerAdditionalDetails(new CustomerAdditionalDetails());
		
		model.addAttribute("customer", customer);
		model.addAttribute("saveType","new");
		return "customer/save-customer";
	}
	
	
	@PostMapping("/save")
	public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult){
		
		// VALIDATIONS
		if(ValidationUtils.checkForErrors(bindingResult)) {
			return "customer/save-customer";
		}
		
		customerCreationValidator.validate(customer, bindingResult);
		
		if(ValidationUtils.checkForErrors(bindingResult)) {
			return "customer/save-customer";
		}
		
		
		// PROCESSING
		boolean encryptPassword = false;
		if(customer.getPassword() != null && !"".equals(customer.getPassword())) {
			encryptPassword=true;
		}
		else {
			customer.setPassword(customerService.getCustomerById(customer.getCustomerId()).getPassword());
		}
		
		customer = customerService.saveCustomer(customer, encryptPassword);
		
		return "redirect:/customer/home?customerId="+customer.getCustomerId();
	}
	
	
	@GetMapping("/update/form")
	public String updateCustomerForm(@RequestParam("customerId") int customerId, Model model){
		
		Customer customer = customerService.getCustomerById(customerId);
		customer.setPassword(null); // PASSWORD WONT BE DISPLAYED, IT ONLY CAN BE CHANGED TO A NEW ONE 
		model.addAttribute("customer", customer);
		model.addAttribute("saveType","update");
		return "customer/save-customer";
	}
	
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int customerId){
		
		customerService.deleteCustomerById(customerId);
		return "redirect:/customer/login/form";
	}

}
