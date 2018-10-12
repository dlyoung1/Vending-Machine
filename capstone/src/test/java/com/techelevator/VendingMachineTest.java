package com.techelevator;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.view.PurchaseMenu;

public class VendingMachineTest {
	
	private VendingMachine test;
	
	
//	public List<InventoryItem> run () {
//		//create new list each time vending machine is run
//		this.purchasedItems = new ArrayList<InventoryItem>();
//		
//		boolean transactionComplete = false;
//		
//		while(!transactionComplete) {
//			String choice = (String)menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS, this.currentBalance);
//			
//			if(choice.equals(PURCHASE_MENU_OPTION_FEED)) {
//				addMoney();
//			} else if(choice.equals(PURCHASE_MENU_OPTION_SELECT)) {
//				String itemSelect = validateSelectionFromUser();
//				if(itemSelect != null) {
//					dispenseItem(itemSelect);
//				}
//			} else if(choice.equals(PURCHASE_MENU_OPTION_FINISH)) {
//				System.out.println("Thank you for your purchase(s)!");
//				BigDecimal[] change = returnChange();
//				System.out.println("Your change is: $" + change[0].setScale(2, BigDecimal.ROUND_HALF_UP) + " in quarters, $" 
//						+ change[1].setScale(2, BigDecimal.ROUND_HALF_UP) + " in dimes, and $" 
//						+ change[2].setScale(2, BigDecimal.ROUND_HALF_UP) + " in nickels.");
//				System.out.println("Current Balance: $" + this.currentBalance.setScale(2, BigDecimal.ROUND_HALF_UP));
//				transactionComplete = true;
//			}
//		}	// close while
//		return this.purchasedItems;
//	}	// close run
	
	@Test
	public void runCompleteTest() {
		List<InventoryItem> result = test.run();
		PURCHASE_MENU_OPTION_FINISH
		Assert.assertEquals(" ", result);
		
	}

}
