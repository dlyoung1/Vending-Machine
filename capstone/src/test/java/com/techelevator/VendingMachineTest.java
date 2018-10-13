package com.techelevator;


import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;


public class VendingMachineTest {
	
	private VendingMachine test = new VendingMachine();
	
	@Test
	public void returns_item_dispensed() {
		InventoryItem result = test.dispenseItem("B2");
		Assert.assertEquals(test.getInventory().get("B2"), result);
		
		result = test.dispenseItem("D3");
		Assert.assertEquals(test.getInventory().get("D3"), result);
		
		result = test.dispenseItem("A1");
		Assert.assertEquals(test.getInventory().get("A1"), result);
		
		result = test.dispenseItem("C4");
		Assert.assertEquals(test.getInventory().get("C4"), result);
	}
	
	@Test
	public void removes_item_dispensed() {
		test.dispenseItem("B2");
		int result = test.getInventory().get("B2").getInventoryRemaining();
		Assert.assertEquals(4, result);
		
		test.dispenseItem("B2");
		result = test.getInventory().get("B2").getInventoryRemaining();
		Assert.assertEquals(3, result);
		
		test.dispenseItem("B2");
		result = test.getInventory().get("B2").getInventoryRemaining();
		Assert.assertEquals(2, result);
		
		test.dispenseItem("B2");
		result = test.getInventory().get("B2").getInventoryRemaining();
		Assert.assertEquals(1, result);
		
		test.dispenseItem("B2");
		result = test.getInventory().get("B2").getInventoryRemaining();
		Assert.assertEquals(0, result);
		
		test.dispenseItem("B2");
		result = test.getInventory().get("B2").getInventoryRemaining();
		Assert.assertEquals(0, result);
	}
	
	@Test
	public void updates_balance_based_on_string_amount() {
		test.updateBalance("39");
		BigDecimal result = test.getCurrentBalance();
		Assert.assertEquals(test.sS(new BigDecimal(39)), result);
		
		test.updateBalance("150");
		result = test.getCurrentBalance();
		Assert.assertEquals(test.sS(new BigDecimal(189)), result);
		
		test.updateBalance("-30");
		result = test.getCurrentBalance();
		Assert.assertEquals(test.sS(new BigDecimal(159)), result);
		
		test.updateBalance("-159");
		result = test.getCurrentBalance();
		Assert.assertEquals(test.sS(new BigDecimal(0)), result);
		
		test.updateBalance(Integer.toString(Integer.MAX_VALUE));
		result = test.getCurrentBalance();
		Assert.assertEquals(test.sS(new BigDecimal(Integer.MAX_VALUE)), result);
		
		test.updateBalance(Integer.toString(Integer.MIN_VALUE));
		result = test.getCurrentBalance();
		Assert.assertEquals(test.sS(new BigDecimal(-1)), result);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void returns_correct_amount_of_change() {
		BigDecimal[] expected = new BigDecimal[] {test.sS(new BigDecimal(1)), test.sS(new BigDecimal(0.10)), test.sS(new BigDecimal(0.05))};
		test.updateBalance("1.15");
		BigDecimal[] result = test.returnChange();
		Assert.assertEquals(expected, result);
		
		expected = new BigDecimal[] {test.sS(new BigDecimal(8.25)), test.sS(new BigDecimal(0.10)), test.sS(new BigDecimal(0.05))};
		test.updateBalance("8.40");
		result = test.returnChange();
		Assert.assertEquals(expected, result);
	}
	


}
