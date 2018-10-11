package com.techelevator;

import java.math.BigDecimal;

public class InventoryItem {
	//class properties
	protected String productName;
	protected String type;
	protected BigDecimal price;
	protected int inventoryRemaining;
	
	//constructor
	public InventoryItem (String productName) {
		this.productName = productName;
		this.type = type;
		this.price = price;
		inventoryRemaining = 5;
	}

	//getters and setters
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getInventoryRemaining() {
		return inventoryRemaining;
	}
	public void setInventoryRemaining(int inventoryRemaining) {
		this.inventoryRemaining = inventoryRemaining;
	}
	
	//methods?
	
	
	@Override
	public String toString() {
		String output = this.productName + "costs $" + this.price + ". There are " + this.inventoryRemaining + " remaining";
		return output;
	}
}
