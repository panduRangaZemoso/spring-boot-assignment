package com.springboot.assignment.model;

import java.util.Date;
import java.util.List;

public class OrderInfo {

	private int orderId;
	private Date expectedDeliveryDate;
	private double orderPrice;
	private List<ItemInfo> items;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Date getExpectedDeliveryDate() {
		return expectedDeliveryDate;
	}
	public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
		this.expectedDeliveryDate = expectedDeliveryDate;
	}
	public double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}
	public List<ItemInfo> getItems() {
		return items;
	}
	public void setItems(List<ItemInfo> items) {
		this.items = items;
	}
	
	
	@Override
	public String toString() {
		return "OrderInfo [orderId=" + orderId + ", expectedDeliveryDate=" + expectedDeliveryDate + ", orderPrice="
				+ orderPrice + ", items=" + items + "]";
	}

}
