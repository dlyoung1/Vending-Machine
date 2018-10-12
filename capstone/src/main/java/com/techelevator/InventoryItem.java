package com.techelevator;

import java.math.BigDecimal;

public class InventoryItem {
	//class properties
	private String productName;
	private String type;
	private BigDecimal price;
	private int inventoryRemaining;
	
	//constructor
	public InventoryItem (String productName, BigDecimal price, String type) {
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
	public void removeOneItem() {
		if(this.inventoryRemaining > 0) {
			this.inventoryRemaining -= 1;
		} else {
			System.out.println("No inventory remaining!");
		}
	}
	
	@Override
	public String toString() {
		//String output = "Type: " + this.type + ". Item: " + this.productName + " costs $" + this.price + ". There are " + this.inventoryRemaining + " remaining";
		String output = "Item: " + this.productName + " costs $" + this.price;
		return output;
	}
}
