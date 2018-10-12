package com.techelevator.view;

import com.techelevator.view.PurchaseMenu;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PurchaseMenuTest {

	private ByteArrayOutputStream output;
	
	private PurchaseMenu getMenuForTestingWithUserInput(String userInput) {
		ByteArrayInputStream input = new ByteArrayInputStream(String.valueOf(userInput).getBytes());
		return new PurchaseMenu(input, output);
	}
	private PurchaseMenu getMenuForTesting() {
		return getMenuForTestingWithUserInput("1\n");
	}
	
	@Before
	public void setup() {
		output = new ByteArrayOutputStream();
	}
	
	@Test
	public void basicGetChoiceFromOptionsTest() {
		Object[] options = new Object[] {  new Integer(3), "Two", "One" };
		PurchaseMenu menu = getMenuForTesting();
		
		menu.getChoiceFromOptions(options);
		
		String expected = "\n"+
				 		  "1) "+options[0].toString()+"\n" + 
						  "2) "+options[1].toString()+"\n" +
						  "3) "+options[2].toString()+"\n\n" +
						  "Please choose an option >>> ";
		Assert.assertEquals(expected, output.toString());	  ;
	}

	@Test
	public void returnUserChoiceTest() {
		Integer expected = new Integer(123);
		Integer[] options = new Integer[] {  new Integer(456), expected, new Integer(987) };
		PurchaseMenu menu = getMenuForTestingWithUserInput("2\n");

		Integer result = (Integer)menu.getChoiceFromOptions(options);
		
		Assert.assertEquals(expected, result);	  
	}
	
	@Test
	public void nonValidOptionTest() {
		Object[] options = new Object[] {  "B", "B", "T" };
		PurchaseMenu menu = getMenuForTestingWithUserInput("4\n1\n");
		
		menu.getChoiceFromOptions(options);
		
		String menuDisplay = "\n"+
				 			 "1) "+options[0].toString()+"\n" + 
						     "2) "+options[1].toString()+"\n" +
						     "3) "+options[2].toString()+"\n\n" +
						     "Please choose an option >>> ";
		
		String expected = menuDisplay + 
					      "\n*** 4 is not a valid option ***\n\n" +
					      menuDisplay;
		
		Assert.assertEquals(expected, output.toString());
	}

}
