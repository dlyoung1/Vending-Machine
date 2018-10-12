package com.techelevator.view;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.techelevator.InventoryItem;

public class InventoryItemTest {
	
	private InventoryItem test = new InventoryItem("test", new BigDecimal(0.0), "test");
	
	
	@Test
	public void removeAnItem() {
		test.removeOneItem();
		int result = test.getInventoryRemaining();
		Assert.assertEquals("Test 1", 4,  result);
		
		test.removeOneItem();
		result = test.getInventoryRemaining();
		Assert.assertEquals("Test 2", 3,  result);
		
		test.removeOneItem();
		result = test.getInventoryRemaining();
		Assert.assertEquals("Test 3", 2,  result);
		
		test.removeOneItem();
		result = test.getInventoryRemaining();
		Assert.assertEquals("Test 4", 1,  result);
		
		test.removeOneItem();
		result = test.getInventoryRemaining();
		Assert.assertEquals("Test 5", 0,  result);
		
		test.removeOneItem();
		result = test.getInventoryRemaining();
		Assert.assertEquals("Test 6", 0,  result);
	}

	@Test
	public void itemSoundTest() {
		String result = test.itemSound("chip");
		Assert.assertEquals("Crunch Crunch, Yum!", result);
	
		result = test.itemSound("candy");
		Assert.assertEquals("Munch Munch, Yum!", result);
		
		result = test.itemSound("drink");
		Assert.assertEquals("Glug Glug, Yum!", result);
	
		result = test.itemSound("gum");
		Assert.assertEquals("Chew Chew, Yum!", result);
	}
	
	@Test
	public void itemSoundNotEqualsTest() {
		String result = test.itemSound("chip");
		Assert.assertNotEquals("Munch Munch, Yum!", result);
	}
	
	
	@Test
	public void toStringTest() {
		String result = test.toString();
		Assert.assertEquals("Item: test costs $0", result);
	}
}
