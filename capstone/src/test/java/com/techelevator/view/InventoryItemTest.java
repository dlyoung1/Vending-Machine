package com.techelevator.view;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.techelevator.InventoryItem;

public class InventoryItemTest {
	
	private InventoryItem test = new InventoryItem("test", "test", new BigDecimal(0.0));
	
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

}
