package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.techelevator.view.Menu;
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
	private List purchasedItems;			//Maybe???
	private File log = null;
	private File salesReport = null;
	private PrintWriter logWriter = null;
	private PrintWriter salesReportWriter = null;
	
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm:ss a"); 
	
	public VendingMachine () {
		this.inventory = setInventory();
		this.currentBalance = new BigDecimal(0.0);
		this.menu = new PurchaseMenu(System.in, System.out);
		
		//create log file and logWriter at time of instantiation
		this.log = new File("log.csv");
		try {
			log.createNewFile();
			this.logWriter = new PrintWriter(this.log);
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
	
	private Map<String, InventoryItem> setInventory () {
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
	
	//Class Methods
	
	public void run () {
		boolean transactionComplete = false;
		
		while(!transactionComplete) {
			String choice = (String)menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS, this.currentBalance);
			
			if(choice.equals(PURCHASE_MENU_OPTION_FEED)) {
				addMoney();
			} else if(choice.equals(PURCHASE_MENU_OPTION_SELECT)) {
				validateSelectionFromUser();
			} else if(choice.equals(PURCHASE_MENU_OPTION_FINISH)) {
				System.out.println("Thank you for your purchase(s)!");
				BigDecimal[] change = returnChange();
				System.out.println("Your change is: $" + change[0].setScale(2, BigDecimal.ROUND_HALF_UP) + " in quarters, $" 
						+ change[1].setScale(2, BigDecimal.ROUND_HALF_UP) + " in dimes, and $" 
						+ change[2].setScale(2, BigDecimal.ROUND_HALF_UP) + " in nickels.");
				System.out.println("Current Balance: $" + this.currentBalance.setScale(2, BigDecimal.ROUND_HALF_UP));
				transactionComplete = true;
			}
		}
	}	// close run
	
	public void displayInventory () {
		
	}	// close displayInventory
	
	public void validateSelectionFromUser () {
		//TODO figure out if/when to close this.
		Scanner input = new Scanner(System.in);
		
		System.out.print("Please enter the product code of the item you wish to purchase: ");
		String productCode = input.nextLine();
		
		if(this.inventory.containsKey(productCode)) {
			if(this.inventory.get(productCode).getInventoryRemaining() > 0) {
				this.inventory.get(productCode).removeOneItem();
				System.out.println("You bought: " + this.inventory.get(productCode));
				//TODO actually purchase item
				//TODO write to log
			} else {
				System.out.println("I'm sorry, but we're out of that product.");
			}
		} else {
			System.out.println("That product does not exist.");
		}
	}	// close validateSelectionFromUser
	
	public InventoryItem dispenseItem () {
		//provide selected item to customer
		return null;
	}	// close dispenseItem
	
	public void addMoney () {
		//validate user input and add money to currentBalance
		String moneyString = "";
		String choice = (String)menu.getChoiceFromOptions(ADD_MONEY_OPTIONS);
			
		if(choice.equals(ADD_MONEY_ONE)) {
			this.currentBalance = this.currentBalance.add(new BigDecimal(1.0));
			moneyString = "$1.00";
		} else if(choice.equals(ADD_MONEY_TWO)) {
			this.currentBalance = this.currentBalance.add(new BigDecimal(2.0));
			moneyString = "$2.00";
		} else if(choice.equals(ADD_MONEY_FIVE)) {
			this.currentBalance = this.currentBalance.add(new BigDecimal(5.0));
			moneyString = "$5.00";
		} else if(choice.equals(ADD_MONEY_TEN)) {
			this.currentBalance = this.currentBalance.add(new BigDecimal(10.0));
			moneyString = "$10.00";
		}
		//TODO - Write to Log		
		writeToLog("FEED MONEY", moneyString);

	}	// close addMoney
	
	public BigDecimal[] returnChange() {
		//Quarters, dimes, and nickels
		BigDecimal[] changeList = new BigDecimal[] {new BigDecimal(0), new BigDecimal(0), new BigDecimal(0)};
		
		while(this.currentBalance.setScale(2, BigDecimal.ROUND_HALF_UP).compareTo(new BigDecimal(0.25)) == 1) {	//while the balance is greater than 0.25
			this.currentBalance = this.currentBalance.subtract(new BigDecimal(0.25));
			changeList[0] = changeList[0].add(new BigDecimal(0.25));
		}
		while(this.currentBalance.setScale(2, BigDecimal.ROUND_HALF_UP).compareTo(new BigDecimal(0.10)) == 1) {	//while the balance is greater than 0.10
			this.currentBalance = this.currentBalance.subtract(new BigDecimal(0.10));
			changeList[1] = changeList[1].add(new BigDecimal(0.10));
		}
		//TODO fix problems here
		while(this.currentBalance.setScale(2, BigDecimal.ROUND_HALF_UP).compareTo(new BigDecimal(0.05)) == 1) {	//while the balance is greater than 0.05
			this.currentBalance = this.currentBalance.subtract(new BigDecimal(0.10));
			changeList[2] = changeList[2].add(new BigDecimal(0.05));
		}
		
		return changeList;
	}	// close returnChange
	
	private void writeToLog(String operation, String moneyString) {
		
		String logString = dtf.format(LocalDateTime.now()) + " " + operation  + " " + moneyString + " $" + this.currentBalance + "\n";
		
		try {
			Files.write(Paths.get("log.csv"), logString.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.out.println("ERROR: WRITE TO LOG FAILED");
			e.printStackTrace();
		}
	}
	
}	// close VendingMachine
