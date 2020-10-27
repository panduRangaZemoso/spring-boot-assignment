package com.springboot.assignment.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CustomerCartId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 70245142012978285L;
	
	@Column(name = "customer_id")
    private int customerId;
 
    @Column(name = "item_id")
    private int itemId;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	@Override
	public String toString() {
		return "CustomerCartId [customerId=" + customerId + ", itemId=" + itemId + "]";
	}
	
}
