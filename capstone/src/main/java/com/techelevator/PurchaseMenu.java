package com.techelevator;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;

import com.techelevator.view.Menu;

public class PurchaseMenu extends Menu {

	public PurchaseMenu(InputStream input, OutputStream output) {
		super(input, output);
		// TODO Auto-generated constructor stub
	}
	
	public Object getChoiceFromOptions(Object[] options, BigDecimal balance) {
		Object choice = null;
		while(choice == null) {
			displayMenuOptions(options, balance);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}
	
	protected void displayMenuOptions(Object[] options, BigDecimal balance) {
		out.println();
		for(int i = 0; i < options.length; i++) {
			int optionNum = i+1;
			out.println(optionNum+") "+options[i]);
		}
		out.println("Current Balance: $" + balance.setScale(2, BigDecimal.ROUND_HALF_UP));
		out.print("\nPlease choose an option >>> ");
		out.flush();
	}

}
