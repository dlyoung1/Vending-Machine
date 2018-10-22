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
//import com.techelevator.view.PurchaseMenu;

public class VendingMachine {
	
	//private static final String filePath = "/Users/mnachman/repos/team-exercises/team3-java-week4-pair-exercises/capstone/vendingmachine.csv";
	private static final String filePath = "vendingmachine.csv";
	
	private BigDecimal currentBalance;
	private BigDecimal totalSales;
	private Map<String, InventoryItem> inventory;
	private Map<String, Integer> itemsSold;
	private List<InventoryItem> purchasedItems;
	private File log = null;
	
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm:ss a"); 
	
	public VendingMachine () {
		this.inventory = stockVendingMachine();
		this.itemsSold = createSalesList(this.inventory);
		resetPurchasedItems();
		this.currentBalance = setScaleShortcut(new BigDecimal(0.0));
		this.totalSales = setScaleShortcut(new BigDecimal(0.0));
		
		//create log file and logWriter at time of instantiation
		this.log = new File("log.csv");
		try {
			log.createNewFile();
			//this.logWriter = new PrintWriter(this.log);
		} catch (IOException e) {
			System.out.println("Log failed to generate");
			e.printStackTrace();
		}
		
	}	// close VendingMachine constructor
	
	//Getters and Setters
	
	public BigDecimal getCurrentBalance () {
		return this.currentBalance;
	}	// close getCurrentBalance
	
	public void addToCurrentBalance (BigDecimal muns) {
		this.currentBalance = this.currentBalance.add(muns);
	}
	
	public BigDecimal getTotalSales() {
		return this.totalSales;
	}
	
	public void addToTotalSales(BigDecimal num) {
		this.totalSales = this.totalSales.add(num);
	}
	
	public Map<String, InventoryItem> getInventory () {
		return this.inventory;
	}	// close getInventory
	
	public void resetPurchasedItems() {
		this.purchasedItems = new ArrayList<InventoryItem>();
	}
	
	public List<InventoryItem> getPurchasedItems() {
		return this.purchasedItems;
	}	// close getPurchasedItems
	
	public Map<String, InventoryItem> stockVendingMachine () {
		Map<String, InventoryItem> returnMap = new LinkedHashMap<String, InventoryItem>();
		
		File file = new File(filePath);
		
		try(Scanner fileScanner = new Scanner(file)) {
			while(fileScanner.hasNextLine()) {
				String[] tempArray = fileScanner.nextLine().split("[|]");
				InventoryItem item = new InventoryItem(tempArray[1], new BigDecimal(tempArray[2]), tempArray[3]);
				returnMap.put(tempArray[0], item);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Vending Machine failed to stock");
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

	public InventoryItem dispenseItem (String productCode) {

		this.inventory.get(productCode).removeOneItem();
		//Add price to running total for sales report
		addToTotalSales(this.inventory.get(productCode).getPrice());
	
		BigDecimal preTransactionBalance = this.currentBalance;
		this.currentBalance = this.currentBalance.subtract(this.inventory.get(productCode).getPrice());
	
		//add item to list of purchased items
		purchasedItems.add(this.inventory.get(productCode));
		
		//note sale in salesList
		//find item string key, increase value by +1
		//MAKE THIS A METHOD??
		Integer a = itemsSold.get(this.inventory.get(productCode).getProductName()) + 1;
		itemsSold.put(this.inventory.get(productCode).getProductName(), a);
		
		String purchaseString = this.inventory.get(productCode).getProductName() + " " + productCode;
		
		writeToLog(purchaseString, preTransactionBalance.toString());
		
		return this.inventory.get(productCode);
	}	// close dispenseItem
	
	public void updateBalance(String money) {
		addToCurrentBalance(new BigDecimal(money));
		writeToLog("FEED MONEY:", money);
	}	// close updateBalance
	
	public BigDecimal[] returnChange() {
		//Quarters, dimes, and nickels
		
		BigDecimal preTransactionBalance = this.currentBalance;
		BigDecimal[] changeList = new BigDecimal[] {new BigDecimal(0), new BigDecimal(0), new BigDecimal(0)};
		
		//Coins
		BigDecimal quarter = setScaleShortcut(new BigDecimal(0.25));
		BigDecimal dime = setScaleShortcut(new BigDecimal(0.10));
		BigDecimal nickel = setScaleShortcut(new BigDecimal(0.05));
		
		while(this.currentBalance.compareTo(quarter) >= 0) {	//while the balance is greater than 0.25
			this.currentBalance = this.currentBalance.subtract(quarter);
			changeList[0] = changeList[0].add(quarter);
		}
		while(this.currentBalance.compareTo(dime) >= 0) {	//while the balance is greater than 0.10
			this.currentBalance = this.currentBalance.subtract(dime);
			changeList[1] = changeList[1].add(dime);
		}
		while(this.currentBalance.compareTo(nickel) >= 0) {	//while the balance is greater than 0.05
			this.currentBalance = this.currentBalance.subtract(nickel);
			changeList[2] = changeList[2].add(nickel);
		}
		
		//Write to log
		writeToLog("GIVE CHANGE:", preTransactionBalance.toString());
		
		return changeList;
	}	// close returnChange
	
	/**
	 * Writes current transaction to the audit log file
	 * @param String operation
	 * @param String moneyString
	 */
	private void writeToLog(String operation, String moneyString) {
		
		//Sample: 
		/*
		10/20/2018 1:33:53 PM FEED MONEY:            1.00      3.00      
		10/20/2018 1:34:02 PM Stackers A2            3.00      1.55      
		10/20/2018 1:34:09 PM Mountain Melter C3     1.55      0.05      
		10/20/2018 1:34:12 PM GIVE CHANGE:           0.05      0.00
		*/
		//writeToLog can handle the date, but needs to know the operation, transaction amount, and balance.
		//Does need 2-3 values passed in. Def 3 if converted to own class
		String logString = String.format("%-22s%-23s%-10s%-10s\n", dtf.format(LocalDateTime.now()), operation, moneyString, this.currentBalance);
		
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
	public BigDecimal setScaleShortcut(BigDecimal bd) {
		//so I don't have to write setScale so many times and clutter things up.
		return bd.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
}	// close VendingMachine
