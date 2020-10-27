package com.springboot.assignment.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderItemId implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = -7367552572219218618L;

	@Column(name = "order_id")
    private int orderId;
 
    @Column(name = "item_id")
    private int itemId;
 
    public OrderItemId() {}
 
    public OrderItemId(int orderId,int itemId) {
        this.orderId = orderId;
        this.itemId = itemId;
    }
 
 
    public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        OrderItemId that = (OrderItemId) o;
        return Objects.equals(orderId, that.orderId) &&
               Objects.equals(itemId, that.itemId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(orderId, itemId);
    }
}