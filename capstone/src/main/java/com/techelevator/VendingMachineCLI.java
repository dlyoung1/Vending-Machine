package com.techelevator;

import java.util.List;

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
				System.out.println(vm.getInventory());
			} else if(choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				//need to call the purchasing menu
				//create new PurchasingMenu (will need Purchasing_Menu_Options String[])
				//or like.... menu.runPurchaseMenu(PURCHASE_MENU_OPTIONS);
				List<InventoryItem> eatThis = vm.run();
				//Put consumption here
				for(int i = 0; i < eatThis.size(); i++) {
					System.out.println(eatThis.get(i).getItemSound());
				}
				
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
