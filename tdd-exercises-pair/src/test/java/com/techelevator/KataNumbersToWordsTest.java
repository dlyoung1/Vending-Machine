package com.techelevator;

import org.junit.*;
import static org.junit.Assert.*;

public class KataNumbersToWordsTest {
	
	@Test
	public void testZero() {
		String result = KataNumbersToWords.convert(0);
		assertEquals("KataNumbersToWords.convert(0)", "zero", result);
	}
	
	@Test
	public void testSingleDigits() {
		String result = KataNumbersToWords.convert(1);
		assertEquals("KataNumbersToWords.convert(1)", "one", result);
		
		result = KataNumbersToWords.convert(2);
		assertEquals("KataNumbersToWords.convert(2)", "two", result);
		
		result = KataNumbersToWords.convert(3);
		assertEquals("KataNumbersToWords.convert(3)", "three", result);
		
		result = KataNumbersToWords.convert(4);
		assertEquals("KataNumbersToWords.convert(4)", "four", result);
		
		result = KataNumbersToWords.convert(5);
		assertEquals("KataNumbersToWords.convert(5)", "five", result);
		
		result = KataNumbersToWords.convert(6);
		assertEquals("KataNumbersToWords.convert(6)", "six", result);
		
		result = KataNumbersToWords.convert(7);
		assertEquals("KataNumbersToWords.convert(7)", "seven", result);
		
		result = KataNumbersToWords.convert(8);
		assertEquals("KataNumbersToWords.convert(8)", "eight", result);
		
		result = KataNumbersToWords.convert(9);
		assertEquals("KataNumbersToWords.convert(9)", "nine", result);
	}
	
	@Test
	public void testTensAndTeens() {
		String result = KataNumbersToWords.convert(10);
		assertEquals("KataNumbersToWords.convert(10)", "ten", result);
		
		result = KataNumbersToWords.convert(11);
		assertEquals("KataNumbersToWords.convert(11)", "eleven", result);
		
		result = KataNumbersToWords.convert(12);
		assertEquals("KataNumbersToWords.convert(12)", "twelve", result);
		
		result = KataNumbersToWords.convert(13);
		assertEquals("KataNumbersToWords.convert(13)", "thirteen", result);
		
		result = KataNumbersToWords.convert(14);
		assertEquals("KataNumbersToWords.convert(14)", "fourteen", result);
		
		result = KataNumbersToWords.convert(15);
		assertEquals("KataNumbersToWords.convert(15)", "fifteen", result);
		
		result = KataNumbersToWords.convert(16);
		assertEquals("KataNumbersToWords.convert(16)", "sixteen", result);
		
		result = KataNumbersToWords.convert(17);
		assertEquals("KataNumbersToWords.convert(17)", "seventeen", result);
		
		result = KataNumbersToWords.convert(18);
		assertEquals("KataNumbersToWords.convert(18)", "eighteen", result);
		
		result = KataNumbersToWords.convert(19);
		assertEquals("KataNumbersToWords.convert(19)", "nineteen", result);
	}
	
	@Test
	public void testTwentyToNinetyNine() {
		String result = KataNumbersToWords.convert(20);
		assertEquals("KataNumbersToWords.convert(20)", "twenty", result);
		
		result = KataNumbersToWords.convert(99);
		assertEquals("KataNumbersToWords.convert(99)", "ninety-nine", result);
		
		result = KataNumbersToWords.convert(42);
		assertEquals("KataNumbersToWords.convert(42)", "forty-two", result);
		
		result = KataNumbersToWords.convert(51);
		assertEquals("KataNumbersToWords.convert(51)", "fifty-one", result);
		
		result = KataNumbersToWords.convert(64);
		assertEquals("KataNumbersToWords.convert(64)", "sixty-four", result);
		
		result = KataNumbersToWords.convert(87);
		assertEquals("KataNumbersToWords.convert(87)", "eighty-seven", result);
	}
	
	@Test
	public void testThreeDigitNumbers() {
		String result = KataNumbersToWords.convert(100);
		assertEquals("KataNumbersToWords.convert(100)", "one hundred", result);
		
		result = KataNumbersToWords.convert(999);
		assertEquals("KataNumbersToWords.convert(999)", "nine hundred and ninety-nine", result);
		
		result = KataNumbersToWords.convert(500);
		assertEquals("KataNumbersToWords.convert(500)", "five hundred", result);
		
		result = KataNumbersToWords.convert(209);
		assertEquals("KataNumbersToWords.convert(209)", "two hundred and nine", result);
		
		result = KataNumbersToWords.convert(498);
		assertEquals("KataNumbersToWords.convert(498)", "four hundred and ninety-eight", result);
		
		result = KataNumbersToWords.convert(413);
		assertEquals("KataNumbersToWords.convert(413)", "four hundred and thirteen", result);
	}
	
	@Test
	public void testFourDigitNumbers() {
		String result = KataNumbersToWords.convert(1000);
		assertEquals("KataNumbersToWords.convert(1000)", "one thousand", result);
		
		result = KataNumbersToWords.convert(9999);
		assertEquals("KataNumbersToWords.convert(9999)", "nine thousand and nine hundred and ninety-nine", result);
		
		result = KataNumbersToWords.convert(3004);
		assertEquals("KataNumbersToWords.convert(3004)", "three thousand and four", result);
		
		result = KataNumbersToWords.convert(5026);
		assertEquals("KataNumbersToWords.convert(5026)", "five thousand and twenty-six", result);
		
		result = KataNumbersToWords.convert(7111);
		assertEquals("KataNumbersToWords.convert(7111)", "seven thousand and one hundred and eleven", result);
	}
	
	@Test
	public void testFiveDigitNumbers() {
		String result = KataNumbersToWords.convert(10000);
		assertEquals("KataNumbersToWords.convert(10000)", "ten thousand", result);
		
		result = KataNumbersToWords.convert(99999);
		assertEquals("KataNumbersToWords.convert(99999)", "ninety-nine thousand and nine hundred and ninety-nine", result);
		
		result = KataNumbersToWords.convert(40000);
		assertEquals("KataNumbersToWords.convert(40000)", "forty thousand", result);
		
		result = KataNumbersToWords.convert(87654);
		assertEquals("KataNumbersToWords.convert(87654)", "eighty-seven thousand and six hundred and fifty-four", result);
		
		result = KataNumbersToWords.convert(54013);
		assertEquals("KataNumbersToWords.convert(54013)", "fifty-four thousand and thirteen", result);
	}
	
	@Test
	public void testSixDigitNumbers() {
		String result = KataNumbersToWords.convert(100000);
		assertEquals("KataNumbersToWords.convert(100000)", "one hundred thousand", result);
		
		result = KataNumbersToWords.convert(999999);
		assertEquals("KataNumbersToWords.convert(999999)", "nine hundred and ninety-nine thousand and nine hundred and ninety-nine", result);
		
		result = KataNumbersToWords.convert(803308);
		assertEquals("KataNumbersToWords.convert(803308)", "eight hundred and three thousand and three hundred and eight", result);
	}

}
