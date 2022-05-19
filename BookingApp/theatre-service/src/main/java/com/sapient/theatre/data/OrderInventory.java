package com.sapient.theatre.data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderInventory {

	@Id
	private Long productId;
	private int availableInventory;
	
	public int getAvailableInventory() {
		return availableInventory;
	}
	public void setAvailableInventory(int availableInventory) {
		this.availableInventory = availableInventory;
	}
	public Long getProductId() {
		return productId;
	}

	
}
