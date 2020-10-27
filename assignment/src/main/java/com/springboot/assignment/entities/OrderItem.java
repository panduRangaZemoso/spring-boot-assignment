package com.springboot.assignment.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="order_item")
public class OrderItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderItemId orderItemId = new OrderItemId();
	
	//@Id
	@ManyToOne
	@JoinColumn(name="order_id",referencedColumnName="order_id")
	@MapsId("orderId")
	private OrderDetails order;
	
	//@Id
	@ManyToOne
	@JoinColumn(name="item_id",referencedColumnName="item_id")
	@MapsId("itemId")
	private ItemDetails item;
	
	@Column(name="quantity")
	private int quantity;
	
	public OrderItem() {}

	public OrderItem(OrderDetails order, ItemDetails item, int quantity) {
		this.orderItemId = new OrderItemId(order.getOrderId(),item.getItemId());
		this.order = order;
		this.item = item;
		this.quantity = quantity;
	}

	public OrderItemId getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(OrderItemId orderItemId) {
		this.orderItemId = orderItemId;
	}

	public OrderDetails getOrder() {
		return order;
	}

	public void setOrder(OrderDetails order) {
		this.order = order;
	}

	public ItemDetails getItem() {
		return item;
	}

	public void setItem(ItemDetails item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderItem [order=" + order + ", item=" + item + ", quantity=" + quantity + "]";
	}
	
}
