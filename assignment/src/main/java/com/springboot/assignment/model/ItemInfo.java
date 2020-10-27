package com.springboot.assignment.model;

public class ItemInfo {

	private String itemName;
	private double unitPrice;
	private int quantity;
	
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "ItemInfo [itemName=" + itemName + ", unitPrice=" + unitPrice + ", quantity=" + quantity + "]";
	}
	
}
