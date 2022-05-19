package com.sapient.theatre.data;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderInventoryConsumption {

	@Id
    private UUID orderId;
    private int productId;
    private int quantityConsumed;
    
	public UUID getOrderId() {
		return orderId;
	}
	
	public void setOrderId(UUID orderId) {
		this.orderId = orderId;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public int getQuantityConsumed() {
		return quantityConsumed;
	}
	
	public void setQuantityConsumed(int quantityConsumed) {
		this.quantityConsumed = quantityConsumed;
	}
	
}
