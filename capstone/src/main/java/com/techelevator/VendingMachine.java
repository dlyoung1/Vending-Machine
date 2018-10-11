package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.techelevator.view.Menu;

public class VendingMachine {

	private static final String PURCHASE_MENU_OPTION_FEED = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED, 
														  PURCHASE_MENU_OPTION_SELECT, PURCHASE_MENU_OPTION_FINISH};
	
	private static final String filePath = "/Users/mnachman/repos/team-exercises/team3-java-week4-pair-exercises/capstone/vendingmachine.csv";
	
	//Money options list
	private static final String ADD_MONEY_ONE = "Add $1";
	private static final String ADD_MONEY_TWO = "Add $2";
	private static final String ADD_MONEY_FIVE = "Add $5";
	private static final String ADD_MONEY_TEN = "Add $10";
	private static final String[] ADD_MONEY_OPTIONS = {ADD_MONEY_ONE, ADD_MONEY_TWO, ADD_MONEY_FIVE, ADD_MONEY_TEN};
	
	PurchaseMenu menu;
	BigDecimal currentBalance;
	Map<String, InventoryItem> inventory;
	List purchasedItems;			//Maybe???
	
	public VendingMachine () {
		this.inventory = setInventory();
		this.currentBalance = new BigDecimal(0.0);
		this.menu = new PurchaseMenu(System.in, System.out);
	}
	
	//Getters and Setters
	
	public BigDecimal getCurrentBalance () {
		return this.currentBalance;
	}
	
	public Map<String, InventoryItem> getInventory () {
		return this.inventory;
	}
	
	private Map<String, InventoryItem> setInventory () {
		Map<String, InventoryItem> returnMap = new HashMap<String, InventoryItem>();
		
		File file = new File(filePath);
		
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try(Scanner fileScanner = new Scanner(file)) {
			while(fileScanner.hasNextLine()) {
				String[] tempArray = fileScanner.nextLine().split("[|]");
				InventoryItem item = new InventoryItem(tempArray[1], new BigDecimal(tempArray[2]), tempArray[3]);
				returnMap.put(tempArray[0], item);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnMap;
	}
	
	//Class Methods
	
	public void run () {
		boolean transactionComplete = false;
		
		while(!transactionComplete) {
			String choice = (String)menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS, this.currentBalance);
			
			if(choice.equals(PURCHASE_MENU_OPTION_FEED)) {
				addMoney();
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

		String choice = (String)menu.getChoiceFromOptions(ADD_MONEY_OPTIONS);
			
		if(choice.equals(ADD_MONEY_ONE)) {
			this.currentBalance = this.currentBalance.add(new BigDecimal(1.0));
		} else if(choice.equals(ADD_MONEY_TWO)) {
			this.currentBalance = this.currentBalance.add(new BigDecimal(2.0));
		} else if(choice.equals(ADD_MONEY_FIVE)) {
			this.currentBalance = this.currentBalance.add(new BigDecimal(5.0));
		} else if(choice.equals(ADD_MONEY_TEN)) {
			this.currentBalance = this.currentBalance.add(new BigDecimal(10.0));
		}
	}
	
	public BigDecimal returnChange() {
		//at end of transaction, return change to customer in nickles, dimes, and quarters
		return null;
	}
	
}
