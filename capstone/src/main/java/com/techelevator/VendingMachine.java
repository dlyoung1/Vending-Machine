package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//import com.techelevator.view.Menu;
import com.techelevator.view.PurchaseMenu;

public class VendingMachine {

	private static final String PURCHASE_MENU_OPTION_FEED = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED, 
														  PURCHASE_MENU_OPTION_SELECT, PURCHASE_MENU_OPTION_FINISH};
	
	//private static final String filePath = "/Users/mnachman/repos/team-exercises/team3-java-week4-pair-exercises/capstone/vendingmachine.csv";
	private static final String filePath = "vendingmachine.csv";
	
	//Money options list
	private static final String ADD_MONEY_ONE = "Add $1";
	private static final String ADD_MONEY_TWO = "Add $2";
	private static final String ADD_MONEY_FIVE = "Add $5";
	private static final String ADD_MONEY_TEN = "Add $10";
	private static final String[] ADD_MONEY_OPTIONS = {ADD_MONEY_ONE, ADD_MONEY_TWO, ADD_MONEY_FIVE, ADD_MONEY_TEN};
	
	private PurchaseMenu menu;
	private BigDecimal currentBalance;
	private Map<String, InventoryItem> inventory;
	private Map<String, Integer> itemsSold;
	private List<InventoryItem> purchasedItems;
	private File log = null;
	//private File salesReport = null;
	//private PrintWriter logWriter = null;
	//private PrintWriter salesReportWriter = null;
	
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm:ss a"); 
	
	public VendingMachine () {
		this.inventory = stockVendingMachine();
		this.itemsSold = createSalesList(this.inventory);
		this.currentBalance = new BigDecimal(0.0);
		this.menu = new PurchaseMenu(System.in, System.out);
		
		//create log file and logWriter at time of instantiation
		this.log = new File("log.csv");
		try {
			log.createNewFile();
			//this.logWriter = new PrintWriter(this.log);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	// close VendingMachine constructor
	
	//Getters and Setters
	
	public BigDecimal getCurrentBalance () {
		return this.currentBalance;
	}	// close getCurrentBalance
	
	public Map<String, InventoryItem> getInventory () {
		return this.inventory;
	}	// close getInventory
	
	private Map<String, InventoryItem> stockVendingMachine () {
		Map<String, InventoryItem> returnMap = new LinkedHashMap<String, InventoryItem>();
		
		File file = new File(filePath);
		
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
	}	// close setInventory
	
	private Map<String, Integer> createSalesList(Map<String, InventoryItem> itemList) {
		//create itemsSold map
		Map<String, Integer> tempMap = new LinkedHashMap<String, Integer>();
		
		for(InventoryItem a : itemList.values()) {
			tempMap.put(a.getProductName(), 0);
		}
		return tempMap;
	}
	
	public Map<String, Integer> getSalesList() {
		return this.itemsSold;
	}
	
	//Class Methods
	
	public List<InventoryItem> run () {
		//create new list each time vending machine is run
		this.purchasedItems = new ArrayList<InventoryItem>();
		
		boolean transactionComplete = false;
		
		while(!transactionComplete) {
			String choice = (String)menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS, this.currentBalance);
			
			if(choice.equals(PURCHASE_MENU_OPTION_FEED)) {
				addMoney();
			} else if(choice.equals(PURCHASE_MENU_OPTION_SELECT)) {
				String itemSelect = validateSelectionFromUser();
				if(itemSelect != null) {
					dispenseItem(itemSelect);
				}
			} else if(choice.equals(PURCHASE_MENU_OPTION_FINISH)) {
				System.out.println("Thank you for your purchase(s)!");
				BigDecimal[] change = returnChange();
				System.out.println("Your change is: $" + change[0].setScale(2, BigDecimal.ROUND_HALF_UP) + " in quarters, $" 
						+ change[1].setScale(2, BigDecimal.ROUND_HALF_UP) + " in dimes, and $" 
						+ change[2].setScale(2, BigDecimal.ROUND_HALF_UP) + " in nickels.");
				System.out.println("Current Balance: $" + this.currentBalance.setScale(2, BigDecimal.ROUND_HALF_UP));
				transactionComplete = true;
			}
		}	// close while
		return this.purchasedItems;
	}	// close run
	
	public void displayInventory () {
		//Iterate and print out the following:
		//Item Location: Item Name, Price
		//ex: A1: Potato Crisps, $3.05
		//if quantity = 0 then display SOLD OUT
		for(String a : inventory.keySet()) {
			if(inventory.get(a).getInventoryRemaining() > 0) {
				System.out.println(a + ": " 
						+ inventory.get(a).getProductName() 
						+ ", $" 
						+ sS(inventory.get(a).getPrice()));
			} else {
				System.out.println(a + ": SOLD OUT");
			}
		}
		
	}	// close displayInventory
	
	public String validateSelectionFromUser () {
		//TODO figure out if/when to close this.
		Scanner input = new Scanner(System.in);
		
		System.out.print("Please enter the product code of the item you wish to purchase: ");
		String productCode = input.nextLine().toUpperCase();
		
		if(this.inventory.containsKey(productCode)) {
			if(this.inventory.get(productCode).getInventoryRemaining() > 0) {
				if(sS(getCurrentBalance()).compareTo(sS(this.inventory.get(productCode).getPrice())) >= 0) {
					return productCode;
				} else {
					System.out.println("You have not fed enough money to the vending machine to purchase this item.");
					productCode = null;
				}
			} else {
				System.out.println("I'm sorry, but we're out of that product.");
				productCode = null;
			}
		} else {
			System.out.println("Please enter a valid product code.");
			productCode = null;
		}
		return productCode;
	}	// close validateSelectionFromUser
	
	public InventoryItem dispenseItem (String productCode) {

		this.inventory.get(productCode).removeOneItem();
		System.out.println("You bought: " + this.inventory.get(productCode).getProductName());
	
		BigDecimal preTransactionBalance = this.currentBalance;
		this.currentBalance = this.currentBalance.subtract(this.inventory.get(productCode).getPrice());
	
		//add item to list of purchased items
		purchasedItems.add(this.inventory.get(productCode));
		
		//note sale in salesList
		//find item string key, increase value by +1
		//MAKE THIS A METHOD??
		Integer a = itemsSold.get(this.inventory.get(productCode).getProductName()) + 1;
		itemsSold.put(this.inventory.get(productCode).getProductName(), a);
		
		writeToLog(this.inventory.get(productCode).getProductName(), preTransactionBalance.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		return null;
	}	// close dispenseItem
	
	public void addMoney () {
		//validate user input and add money to currentBalance
		String moneyString = "";
		String choice = (String)menu.getChoiceFromOptions(ADD_MONEY_OPTIONS);
			
		if(choice.equals(ADD_MONEY_ONE)) {
			this.currentBalance = this.currentBalance.add(new BigDecimal(1.0));
			moneyString = "1.00";
		} else if(choice.equals(ADD_MONEY_TWO)) {
			this.currentBalance = this.currentBalance.add(new BigDecimal(2.0));
			moneyString = "2.00";
		} else if(choice.equals(ADD_MONEY_FIVE)) {
			this.currentBalance = this.currentBalance.add(new BigDecimal(5.0));
			moneyString = "5.00";
		} else if(choice.equals(ADD_MONEY_TEN)) {
			this.currentBalance = this.currentBalance.add(new BigDecimal(10.0));
			moneyString = "10.00";
		}
		
		writeToLog("FEED MONEY", moneyString);

	}	// close addMoney
	
	public BigDecimal[] returnChange() {
		//Quarters, dimes, and nickels
		
		BigDecimal preTransactionBalance = this.currentBalance;
		BigDecimal[] changeList = new BigDecimal[] {new BigDecimal(0), new BigDecimal(0), new BigDecimal(0)};
		
		//Coins
		BigDecimal quarter = new BigDecimal(0.25);
		BigDecimal dime = new BigDecimal(0.10);
		BigDecimal nickel = new BigDecimal(0.05);
		
		while(sS(this.currentBalance).compareTo(sS(quarter)) >= 0) {	//while the balance is greater than 0.25
			this.currentBalance = sS(this.currentBalance).subtract(sS(quarter));
			changeList[0] = changeList[0].add(sS(quarter));
		}
		while(sS(this.currentBalance).compareTo(sS(dime)) >= 0) {	//while the balance is greater than 0.10
			this.currentBalance = sS(this.currentBalance).subtract(sS(dime));
			changeList[1] = changeList[1].add(sS(dime));
		}
		//TODO fix problems here
		while(sS(this.currentBalance).compareTo(sS(nickel)) >= 0) {	//while the balance is greater than 0.05
			this.currentBalance = sS(this.currentBalance).subtract(sS(nickel));
			changeList[2] = changeList[2].add(sS(nickel));
		}
		
		//Write to log
		writeToLog("GIVE CHANGE", sS(preTransactionBalance).toString());
		
		return changeList;
	}	// close returnChange
	
	/**
	 * Writes current transaction to the audit log file
	 * @param String operation
	 * @param String moneyString
	 */
	private void writeToLog(String operation, String moneyString) {
		
		String logString = dtf.format(LocalDateTime.now()) + " " + operation  + " $" 
				+ moneyString + " $" + sS(this.currentBalance) + "\n";
		
		try {
			Files.write(Paths.get("log.csv"), logString.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.out.println("ERROR: WRITE TO LOG FAILED");
			e.printStackTrace();
		}
	}	// close writeToLog
	
	/**
	 * BigDecimal SetScale
	 * @param bd
	 */
	private BigDecimal sS(BigDecimal bd) {
		//so I don't have to write setScale so many times and clutter things up.
		return bd.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
}	// close VendingMachine
