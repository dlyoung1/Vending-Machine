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
	private Map<String, InventoryItem> inventory;
	private Map<String, Integer> itemsSold;
	private List<InventoryItem> purchasedItems;
	private File log = null;
	
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm:ss a"); 
	
	public VendingMachine () {
		this.inventory = stockVendingMachine();
		this.itemsSold = createSalesList(this.inventory);
		this.purchasedItems = new ArrayList<InventoryItem>();
		this.currentBalance = new BigDecimal(0.0);
		
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
	
	public void addToCurrentBalance (BigDecimal muns) {
		this.currentBalance = sS(this.currentBalance).add(sS(muns));
	}
	
	public Map<String, InventoryItem> getInventory () {
		return this.inventory;
	}	// close getInventory
	
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

	public InventoryItem dispenseItem (String productCode) {

		this.inventory.get(productCode).removeOneItem();
	
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
		
		writeToLog(purchaseString, preTransactionBalance.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		
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
		while(sS(this.currentBalance).compareTo(sS(nickel)) >= 0) {	//while the balance is greater than 0.05
			this.currentBalance = sS(this.currentBalance).subtract(sS(nickel));
			changeList[2] = changeList[2].add(sS(nickel));
		}
		
		//Write to log
		writeToLog("GIVE CHANGE:", sS(preTransactionBalance).toString());
		
		return changeList;
	}	// close returnChange
	
	/**
	 * Writes current transaction to the audit log file
	 * @param String operation
	 * @param String moneyString
	 */
	private void writeToLog(String operation, String moneyString) {
		
		String logString = dtf.format(LocalDateTime.now()) + " " + operation  + "\t\t $" 
				+ moneyString + "\t\t $" + sS(this.currentBalance) + "\n";
		
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
	public BigDecimal sS(BigDecimal bd) {
		//so I don't have to write setScale so many times and clutter things up.
		return bd.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
}	// close VendingMachine
