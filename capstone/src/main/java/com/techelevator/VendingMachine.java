package com.techelevator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.techelevator.view.Menu;

public class VendingMachine {

	private static final String PURCHASE_MENU_OPTION_FEED = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED, 
														  PURCHASE_MENU_OPTION_SELECT, PURCHASE_MENU_OPTION_FINISH};
	
	Menu menu;
	BigDecimal currentBalance;
	Map<String, InventoryItem> inventory;
	List purchasedItems;			//Maybe???
	
	public VendingMachine (Menu menu) {
		this.menu = menu;
		this.inventory = setInventory();
	}
	
	//Getters and Setters
	
	public BigDecimal getCurrentBalance () {
		return this.currentBalance;
	}
	
	public Map<String, InventoryItem> getInventory () {
		return this.inventory;
	}
	
	private Map<String, InventoryItem> setInventory () {
		
		return null;
	}
	
	//Class Methods
	
	public void run () {
		boolean transactionComplete = false;
		
		while(!transactionComplete) {
			String choice = (String)menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
			
			if(choice.equals(PURCHASE_MENU_OPTION_FEED)) {
				System.out.println("Thanks for your money");
			} else if(choice.equals(PURCHASE_MENU_OPTION_SELECT)) {
				System.out.println("Here's a product");
			} else if(choice.equals(PURCHASE_MENU_OPTION_FINISH)) {
				System.out.println("Thank you for your purchase(s)!");
				transactionComplete = true;
			}
		}
	}
	
	public InventoryItem dispenseItem () {
		//provide selected item to customer
		return null;
	}
	
	public void addMoney () {
		//validate user input and add money to currentBalance
	}
	
	public BigDecimal returnChange() {
		//at end of transaction, return change to customer in nickles, dimes, and quarters
		return null;
	}
	
}
