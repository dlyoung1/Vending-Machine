package com.techelevator;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_QUIT = "Quit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
													   MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_QUIT };
	
	private static final String PURCHASE_MENU_OPTION_FEED = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED, 
														  PURCHASE_MENU_OPTION_SELECT, PURCHASE_MENU_OPTION_FINISH};
	
	//Money options list
	private static final String ADD_MONEY_ONE = "Add $1";
	private static final String ADD_MONEY_TWO = "Add $2";
	private static final String ADD_MONEY_FIVE = "Add $5";
	private static final String ADD_MONEY_TEN = "Add $10";
	private static final String ADD_MONEY_QUIT = "Go Back";
	private static final String[] ADD_MONEY_OPTIONS = {ADD_MONEY_ONE, ADD_MONEY_TWO, ADD_MONEY_FIVE, ADD_MONEY_TEN, ADD_MONEY_QUIT};
	
	private Menu menu;
	private VendingMachine vm;
	
	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
		this.vm = new VendingMachine();
	}
	
	public void run() {
		
		vm.stockVendingMachine();
		
		while(true) {
			String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			
			if(choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				displayInventory();
			} else if(choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				//need to call the purchasing menu
				runPurchasingMenu();
				
				List<InventoryItem> eatThis = vm.getPurchasedItems();
				//Put consumption here
				for(int i = 0; i < eatThis.size(); i++) {
					System.out.println(eatThis.get(i).getItemSound());
				}		
				//clean out consumed items
				vm.resetPurchasedItems();
			} else if (choice.equals(MAIN_MENU_OPTION_QUIT)) {
				printSalesReport();
				System.out.println("Ending program");
				System.exit(0);
			}
		}
	}	// close run
	
	private void runPurchasingMenu() {
		//PurchaseMenu pMenu = new PurchaseMenu(System.in, System.out);
		
		boolean transactionComplete = false;
		
		while(!transactionComplete) {
			String choice = (String)menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS, vm.getCurrentBalance());
			
			if(choice.equals(PURCHASE_MENU_OPTION_FEED)) {
				addMoney();
			} else if(choice.equals(PURCHASE_MENU_OPTION_SELECT)) {
				displayInventory();
				System.out.println(""); 	//extra space for formatting
				String itemSelect = validateItemSelectionFromUser();
				if(itemSelect != null) {
					InventoryItem purchasedItem = vm.dispenseItem(itemSelect);
					System.out.println("You bought: " + purchasedItem.getProductName());
				}
			} else if(choice.equals(PURCHASE_MENU_OPTION_FINISH)) {
				System.out.println("Thank you for your purchase(s)!");
				BigDecimal[] change = vm.returnChange();
				System.out.println("Your change is: $" + change[0] + " in quarters, $" 
						+ change[1] + " in dimes, and $" 
						+ change[2] + " in nickels.");
				System.out.println("Current Balance: $" + vm.getCurrentBalance());
				transactionComplete = true;
			}
		}	// close while
	}	// close runPurchasingMenu
	
	//Sys.out needs to go to CLI
	public void displayInventory () {
		for(String a : vm.getInventory().keySet()) {
			if(vm.getInventory().get(a).getInventoryRemaining() > 0) {
				System.out.println(String.format("%-4s%-19s%-7s%-10s", 
						a + ":", 
						vm.getInventory().get(a).getProductName(), 
						"$" + vm.getInventory().get(a).getPrice() + ",", 
						vm.getInventory().get(a).getInventoryRemaining() + " left"));
			} else {
				System.out.println(a + ": SOLD OUT");
			}
		}
		
	}	// close displayInventory

	public void addMoney () {
		boolean isDone = false;
		while(!isDone) {
			//validate user input and add money to currentBalance
			String choice = (String)menu.getChoiceFromOptions(ADD_MONEY_OPTIONS, vm.getCurrentBalance());
			
			if(choice.equals(ADD_MONEY_ONE)) {
				vm.updateBalance("1.00");
			} else if(choice.equals(ADD_MONEY_TWO)) {
				vm.updateBalance("2.00");
			} else if(choice.equals(ADD_MONEY_FIVE)) {
				vm.updateBalance("5.00");
			} else if(choice.equals(ADD_MONEY_TEN)) {
				vm.updateBalance("10.00");
			} else if(choice.equals(ADD_MONEY_QUIT)) {
				isDone = true;
			}
		}
	}	// close addMoney
	
	public String validateItemSelectionFromUser () {
		//TODO figure out if/when to close this.
		Scanner input = new Scanner(System.in);
		
		System.out.print("Please enter the product code of the item you wish to purchase: ");
		String productCode = input.nextLine().toUpperCase();

		if(vm.getInventory().containsKey(productCode)) {
			if(vm.getInventory().get(productCode).getInventoryRemaining() > 0) {
				if(vm.getCurrentBalance().compareTo(vm.getInventory().get(productCode).getPrice()) >= 0) {
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
	
	private void printSalesReport() {
		//fake report
		Map<String, Integer> salesOutput = vm.getSalesList();
		//BONUS: append current date/time to file name
		File salesReport = new File("SalesReport.csv");
		
		//destroy old file to avoid constant appending
		if(salesReport.exists()) {
			salesReport.delete();
		}
		
		try {
			salesReport.createNewFile();
			
			for(String a : salesOutput.keySet()) {
				String salesString = a + "|" + salesOutput.get(a) + "\n";
				Files.write(Paths.get("SalesReport.csv"), salesString.getBytes(), StandardOpenOption.APPEND);
			}
			Files.write(Paths.get("SalesReport.csv"), ("\n**TOTAL SALES** $" + vm.getTotalSales()).getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.out.println("ERROR: WRITE TO SALES REPORT FAILED");
			e.printStackTrace();
		}
	}	// close printSalesReport
	
	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}	// close main
