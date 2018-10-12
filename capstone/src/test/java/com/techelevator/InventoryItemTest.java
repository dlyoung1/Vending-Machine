package com.techelevator;

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
		
		test.setType("chip");
		String result = test.getItemSound();
		Assert.assertEquals("Crunch Crunch, Yum!", result);
	
		test.setType("cANdy");
		result = test.getItemSound();
		Assert.assertEquals("Munch Munch, Yum!", result);
		
		test.setType("drink");
		result = test.getItemSound();
		Assert.assertEquals("Glug Glug, Yum!", result);
	
		test.setType("gum");
		result = test.getItemSound();
		Assert.assertEquals("Chew Chew, Yum!", result);
	}
	
	@Test
	public void itemSoundNotEqualsTest() {
		test.setType("gum");
		String result = test.getItemSound();
		Assert.assertNotEquals("Munch Munch, Yum!", result);
		
		test.setType("drink");
		result = test.getItemSound();
		Assert.assertNotEquals("Crunch Crunch, Yum!", result);
	}
	
	
	@Test
	public void toStringTest() {
		String result = test.toString();
		Assert.assertEquals("Item: test costs $0", result);
	}
}
