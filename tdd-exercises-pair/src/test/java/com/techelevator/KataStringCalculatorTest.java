package com.techelevator;

import org.junit.*;
import static org.junit.Assert.*;

public class KataStringCalculatorTest {
	
	@Test
	public void testEmpty() {
		
		int result = KataStringCalculator.add("");
		assertEquals("KataStringCalculator.add(\"\");", 0, result);
		
	}
	
	@Test
	public void testSingle() {
		int result = KataStringCalculator.add("1");
		assertEquals("KataStringCalculator.add(\"1\");", 1, result);
	}
	
	@Test
	public void testDouble() {
		int result = KataStringCalculator.add("1,2");
		assertEquals("KataStringCalculator.add(\"1,2\");", 3, result);
	}
	
	@Test
	public void testMultiples() {
		int result = KataStringCalculator.add("1,5,7");
		assertEquals("KataStringCalculator.add(\"1,5,7\")", 13, result);
		
		result = KataStringCalculator.add("1,5,7,3");
		assertEquals("KataStringCalculator.add(\"1,5,7,3\")", 16, result);
		
		result = KataStringCalculator.add("1,5,7,3,9");
		assertEquals("KataStringCalculator.add(\"1,5,7,3,9\")", 25, result);
		
	}
	
	@Test
	public void testNewLines() {
		int result = KataStringCalculator.add("1\n2,3");
		assertEquals("KataStringCalculator.add(\"1\\n2,3\")", 6, result);
		
		result = KataStringCalculator.add("1,\n");
		assertEquals("KataStringCalculator.add(\"1\\n\")", 1, result);
	}
	
	@Test
	public void testDelimeters() {
		int result = KataStringCalculator.add("//;\n1;2");
		assertEquals("KataStringCalculator.add(\"//;\\n1;2\")", 3, result);
		
		result = KataStringCalculator.add("//!\n4!9");
		assertEquals("KataStringCalculator.add(\"//!\\n4!9\")", 13, result);
		
//		result = KataStringCalculator.add("//@#\n4@#9");
//		assertEquals("KataStringCalculator.add(\"//@#\\n4!9\")", 13, result);
	}

}
