package com.springboot.assignment.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="item_details")
public class ItemDetails {
	
	//item_id, item_name, price, stock_quantity
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="item_id")
	private int itemId;
	
	@Column(name="item_name")
	@NotNull(message="Please enter a valid Item Name")
	private String itemName;
	
	@Column(name="price")
	@NotNull(message="Please enter a valid Price")
	@Min(value=100,message="Price needs to be atleast Rs.100")
	private Double price;
	
	@Column(name="stock_quantity")
	@NotNull(message="Please enter a valid Stock Quantity")
	@Min(value=1,message="Please enter a valid Stock Quantity")
	private Integer stockQuantity;
	
	@OneToMany(mappedBy= "item",cascade = CascadeType.ALL)
	private List<OrderItem> orderItems;
	
	@OneToMany(mappedBy= "item",cascade = CascadeType.ALL)
	private List<CustomerCartDetails> cartDetails;
	
	public ItemDetails() {}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrders(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return "ItemDetails [itemId=" + itemId + ", itemName=" + itemName + ", price=" + price + ", stockQuantity="
				+ stockQuantity + "]";
	}
	
}
