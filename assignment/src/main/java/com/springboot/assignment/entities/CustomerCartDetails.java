package com.springboot.assignment.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="customer_cart_details")
public class CustomerCartDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7217438695096004771L;

	@EmbeddedId
	private CustomerCartId customerCartId = new CustomerCartId();
	
	@ManyToOne
	@JoinColumn(name="customer_id",referencedColumnName="customer_id")
	@MapsId("customerId")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="item_id",referencedColumnName="item_id")
	@MapsId("itemId")
	private ItemDetails item;
	
	@Min(value=1, message="Quantity should be atleast 1")
	@Max(value=5, message="Quantity should be below or equal to 5")
	@NotNull(message="Please enter a valid quantity")
	@Column(name="quantity")
	private Integer quantity;
	
	public CustomerCartDetails() { }

	public CustomerCartId getCustomerCartId() {
		return customerCartId;
	}

	public void setCustomerCartId(CustomerCartId customerCartId) {
		this.customerCartId = customerCartId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ItemDetails getItem() {
		return item;
	}

	public void setItem(ItemDetails item) {
		this.item = item;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CustomerCartDetails [customerCartId=" + customerCartId + ", customer=" + customer + ", item=" + item
				+ ", quantity=" + quantity + "]";
	}
	
}
