package com.springboot.assignment.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="order_details")
public class OrderDetails {

	// order_id, order_price, expected_delivery_date, customer_id
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="order_id")
	private int orderId;
	
	@Column(name="order_price")
	private double orderPrice;
	
	@Column(name="expected_delivery_date")
	private Date expectedDeliveryDate;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@OneToMany(mappedBy="order",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private List<OrderItem> orderItems;
	
	public OrderDetails() {}

	public OrderDetails(double orderPrice, Date expectedDeliveryDate) {
		this.orderPrice = orderPrice;
		this.expectedDeliveryDate = expectedDeliveryDate;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public Date getExpectedDeliveryDate() {
		return expectedDeliveryDate;
	}

	public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
		this.expectedDeliveryDate = expectedDeliveryDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	public void addItem(ItemDetails item, int quantity) {
		if(this.orderItems == null) {
			this.orderItems = new ArrayList<>();
		}
		
		OrderItem orderItem = new OrderItem(this,item, quantity);
		this.orderItems.add(orderItem);
	}

	
	@Override
	public String toString() {
		return "OrderDetails [orderId=" + orderId + ", orderPrice=" + orderPrice + ", expectedDeliveryDate="
				+ expectedDeliveryDate + ", customer=" + customer + "]";
	}
	
	/*
	public String displayOrder() {
		String orderItemDetails = " [";
		for(OrderItem orderItem : this.getOrderItems()) {
			orderItemDetails += " [";
			orderItemDetails += orderItem.getItem();
			orderItemDetails += ", Quantity="+orderItem.getQuantity();
			orderItemDetails += "],";
		}
		orderItemDetails += "]";
		
		
		return "OrderDetails [orderId=" + orderId + ", orderPrice=" + orderPrice + ", expectedDeliveryDate="
				+ expectedDeliveryDate + ", OrderItemsDetails="+ orderItemDetails +"]";
	}*/
	
}
