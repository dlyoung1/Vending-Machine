package com.techelevator;

import org.junit.*;
import static org.junit.Assert.*;

public class KataWordsToNumbersTest {

	@Test
	public void testZero() {
		int result = KataWordsToNumbers.convertToInt("zero");
		assertEquals("KataWordsToNumbers.convertToInt(\"zero\")", 0, result);
	}
	
	@Test
	public void testSingleDigits() {
		int result = KataWordsToNumbers.convertToInt("one");
		assertEquals("KataWordsToNumbers.convertToInt(\"one\")", 1, result);
		
		result = KataWordsToNumbers.convertToInt("two");
		assertEquals("KataWordsToNumbers.convertToInt(\"two\")", 2, result);
		
		result = KataWordsToNumbers.convertToInt("three");
		assertEquals("KataWordsToNumbers.convertToInt(\"three\")", 3, result);
		
		result = KataWordsToNumbers.convertToInt("seven");
		assertEquals("KataWordsToNumbers.convertToInt(\"seven\")", 7, result);
		
		result = KataWordsToNumbers.convertToInt("nine");
		assertEquals("KataWordsToNumbers.convertToInt(\"nine\")", 9, result);
	}
	
	@Test
	public void testDoubleDigits() {
		int result = KataWordsToNumbers.convertToInt("eleven");
		assertEquals("KataWordsToNumbers.convertToInt(\"eleven\")", 11, result);
		
		result = KataWordsToNumbers.convertToInt("twelve");
		assertEquals("KataWordsToNumbers.convertToInt(\"twelve\")", 12, result);
		
		result = KataWordsToNumbers.convertToInt("ten");
		assertEquals("KataWordsToNumbers.convertToInt(\"ten\")", 10, result);
		
		result = KataWordsToNumbers.convertToInt("seventeen");
		assertEquals("KataWordsToNumbers.convertToInt(\"seventeen\")", 17, result);
		
		result = KataWordsToNumbers.convertToInt("nineteen");
		assertEquals("KataWordsToNumbers.convertToInt(\"nineteen\")", 19, result);
		
		result = KataWordsToNumbers.convertToInt("SIXTEEN");
		assertEquals("KataWordsToNumbers.convertToInt(\"SIXTEEN\")", 16, result);
		
		result = KataWordsToNumbers.convertToInt("seventy-two");
		assertEquals("KataWordsToNumbers.convertToInt(\"seventy-two\")", 72, result);
	}
	
	@Test
	public void testThreeDigits() {
		int result = KataWordsToNumbers.convertToInt("one hundred");
		assertEquals("KataWordsToNumbers.convertToInt(\"one hundred\")", 100, result);
		
		result = KataWordsToNumbers.convertToInt("one hundred and sixty-five");
		assertEquals("KataWordsToNumbers.convertToInt(\"one hundred and sixty-five\")", 165, result);
		
		result = KataWordsToNumbers.convertToInt("nine hundred and ninety-nine");
		assertEquals("KataWordsToNumbers.convertToInt(\"nine hundred and ninety-nine\")", 999, result);
		
		result = KataWordsToNumbers.convertToInt("two hundred and five");
		assertEquals("KataWordsToNumbers.convertToInt(\"two hundred and five\")", 205, result);
		
		result = KataWordsToNumbers.convertToInt("three hundred and sixty");
		assertEquals("KataWordsToNumbers.convertToInt(\"three hundred and sixty\")", 360, result);
		
		result = KataWordsToNumbers.convertToInt("four hundred and thirteen");
		assertEquals("KataWordsToNumbers.convertToInt(\"four hundred and thirteen\")", 413, result);
		
		result = KataWordsToNumbers.convertToInt("six hundred and sixty-six");
		assertEquals("KataWordsToNumbers.convertToInt(\"six hundred and sixty-six\")", 666, result);
	}
	
	@Test
	public void testFourDigits() {
		int result = KataWordsToNumbers.convertToInt("one thousand");
		assertEquals("KataWordsToNumbers.convertToInt(\"one thousand\")", 1000, result);
		
		result = KataWordsToNumbers.convertToInt("nine thousand and nine hundred and ninety-nine");
		assertEquals("KataWordsToNumbers.convertToInt(\"nine thousand nine hundred and ninety-nine\")", 9999, result);
		
		result = KataWordsToNumbers.convertToInt("one thousand and six hundred and sixty-five");
		assertEquals("KataWordsToNumbers.convertToInt(\"one thousand and six hundred and sixty-five\")", 1665, result);
		
		result = KataWordsToNumbers.convertToInt("two thousand and fifty-seven");
		assertEquals("KataWordsToNumbers.convertToInt(\"two thousand and fifty-seven\")", 2057, result);
		
		result = KataWordsToNumbers.convertToInt("five thousand and nine hundred and sixteen");
		assertEquals("KataWordsToNumbers.convertToInt(\"five thousand and nine hundred and sixteen\")", 5916, result);
		
		result = KataWordsToNumbers.convertToInt("three thousand and one");
		assertEquals("KataWordsToNumbers.convertToInt(\"three thousand and one\")", 3001, result);
	}
	
	@Test 
	public void testFiveDigits() {
		int result = KataWordsToNumbers.convertToInt("ten thousand");
		assertEquals("KataWordsToNumbers.convertToInt(\"ten thousand\")", 10000, result);
		
		result = KataWordsToNumbers.convertToInt("ninety-nine thousand and nine hundred and ninety-nine");
		assertEquals("KataWordsToNumbers.convertToInt(\"ninety-nin thousand nine hundred and ninety-nine\")", 99999, result);
		
		result = KataWordsToNumbers.convertToInt("fifty thousand and six");
		assertEquals("KataWordsToNumbers.convertToInt(\"fifty thousand and six\")", 50006, result);
		
		result = KataWordsToNumbers.convertToInt("thirty thousand and seventeen");
		assertEquals("KataWordsToNumbers.convertToInt(\"thirty thousand and seventeen\")", 30017, result);
		
		result = KataWordsToNumbers.convertToInt("forty-two thousand and four hundred and four");
		assertEquals("KataWordsToNumbers.convertToInt(\"forty-two thousand and four hundred and four\")", 42404, result);
	}
	
	@Test
	public void testSixDigits() {
		int result = KataWordsToNumbers.convertToInt("one hundred thousand");
		assertEquals("KataWordsToNumbers.convertToInt(\"one hundred thousand\")", 100000, result);
		
		result = KataWordsToNumbers.convertToInt("nine hundred and ninety-nine thousand and nine hundred and ninety-nine");
		assertEquals("KataWordsToNumbers.convertToInt(\"nine hundred and ninety-nin thousand nine hundred and ninety-nine\")", 999999, result);
		
		result = KataWordsToNumbers.convertToInt("nine hundred thousand and six");
		assertEquals("KataWordsToNumbers.convertToInt(\"nine hundred thousand and six\")", 900006, result);
		
		result = KataWordsToNumbers.convertToInt("eight hundred and sixteen thousand and eight hundred and sixteen");
		assertEquals("KataWordsToNumbers.convertToInt(\"eight hundred and sixteen thousand and eight hundred and sixteen\")", 816816, result);
	}
	
}
