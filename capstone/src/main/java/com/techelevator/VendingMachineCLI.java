package com.techelevator;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_QUIT = "Quit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
													   MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_QUIT };
	
	private Menu menu;
	
	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}
	
	public void run() {
		
		VendingMachine vm = new VendingMachine();
		
		while(true) {
			String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			
			if(choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				System.out.println("Default: These are the items for sale");
			} else if(choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				//need to call the purchasing menu
				//create new PurchasingMenu (will need Purchasing_Menu_Options String[])
				//or like.... menu.runPurchaseMenu(PURCHASE_MENU_OPTIONS);
				vm.run();
			} else if (choice.equals(MAIN_MENU_OPTION_QUIT)) {
				System.out.println("Ending program");
				System.exit(0);
			}
		}
	}
	
	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
