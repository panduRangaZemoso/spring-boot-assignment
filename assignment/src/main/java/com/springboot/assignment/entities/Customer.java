package com.springboot.assignment.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="customer")
public class Customer {

	//customer_id, customer_name, email, password, created_time, modified_time
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customer_id")
	private int customerId;
	
	@Column(name="customer_name")
	@NotNull(message="Please enter a valid name")
	private String customerName;
	
	@Column(name="email")
	@NotNull(message="Please enter a valid email")
	@Email(message="Please enter a valid email")
	private String email;
	
	@Column(name="address")
	@NotNull(message="Please enter a valid address")
	private String address;
	
	@Column(name="password")
	//@NotNull(message="Please enter a valid password")
	private String password;
	
	@OneToOne(mappedBy="customer", cascade = CascadeType.ALL)
	private CustomerAdditionalDetails customerAdditionalDetails;
	
	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL) //fetch=FetchType.EAGER
	private List<OrderDetails> orders;
	
	@OneToMany(mappedBy="customer",cascade = CascadeType.ALL) //fetch=FetchType.LAZY
	private List<CustomerCartDetails> cartDetails;
	
	public Customer() {}

	public Customer(String customerName, String email, String password) {
		this.customerName = customerName;
		this.email = email;
		this.password = password;
	}

	
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	public CustomerAdditionalDetails getCustomerAdditionalDetails() {
		return customerAdditionalDetails;
	}

	public void setCustomerAdditionalDetails(CustomerAdditionalDetails customerAdditionalDetails) {
		this.customerAdditionalDetails = customerAdditionalDetails;
	}

	
	
	public List<OrderDetails> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDetails> orders) {
		this.orders = orders;
	}
	
	public void addOrder(OrderDetails order) {
		if(this.orders == null) {
			this.orders = new ArrayList<>();
		}
		// ADD ORDER FOR CUSTOMER
		this.orders.add(order);
		// SET CUSTOMER FOR THAT ORDER TO THIS CUSTOMER
		order.setCustomer(this);
	}

	
	
	
	 public List<CustomerCartDetails> getCartDetails() {
		 return cartDetails;  
	 }
	 
	 public void setCartDetails(List<CustomerCartDetails> cartDetails) {
		 this.cartDetails = cartDetails; 
	 }
	  
	  public void addCartDetails(CustomerCartDetails cartDetails) {
		  if(this.cartDetails == null) {
			  this.cartDetails = new ArrayList<>(); 
		  } 
		  // ADD CART ITEM FOR CUSTOMER
		  this.cartDetails.add(cartDetails); 
		  // SET CUSTOMER FOR THAT CART DETAIL TO THIS CUSTOMER
		  cartDetails.setCustomer(this);
	  }

	
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", email=" + email
				+ ", address=" + address + ", password=" + password + ", customerAdditionalDetails="
				+ customerAdditionalDetails + "]";
	}
	
}
