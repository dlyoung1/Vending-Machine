package com.techelevator;

import java.util.HashMap;
import java.util.Map;

public class KataNumbersToWords {

	public static String convert(int nums) {

		Map<Integer, String> numWordsMap = new HashMap<Integer, String>();

		String numWord = "";

		numWordsMap.put(0, "zero");
		numWordsMap.put(1, "one");
		numWordsMap.put(2, "two");
		numWordsMap.put(3, "three");
		numWordsMap.put(4, "four");
		numWordsMap.put(5, "five");
		numWordsMap.put(6, "six");
		numWordsMap.put(7, "seven");
		numWordsMap.put(8, "eight");
		numWordsMap.put(9, "nine");

		numWordsMap.put(10, "ten");
		numWordsMap.put(11, "eleven");
		numWordsMap.put(12, "twelve");
		numWordsMap.put(13, "thirteen");
		numWordsMap.put(14, "fourteen");
		numWordsMap.put(15, "fifteen");
		numWordsMap.put(16, "sixteen");
		numWordsMap.put(17, "seventeen");
		numWordsMap.put(18, "eighteen");
		numWordsMap.put(19, "nineteen");

		numWordsMap.put(20, "twenty");
		numWordsMap.put(30, "thirty");
		numWordsMap.put(40, "forty");
		numWordsMap.put(50, "fifty");
		numWordsMap.put(60, "sixty");
		numWordsMap.put(70, "seventy");
		numWordsMap.put(80, "eighty");
		numWordsMap.put(90, "ninety");

		// numWordsMap.put(100, "hundred");

		// check for zero
		if (nums == 0) {
			return numWordsMap.get(nums);
		} else if (nums < 1000) {
			return get1To999(nums, numWord, numWordsMap);
		} else {
			return get1000To999999(nums, numWord, numWordsMap);
		}
	} // close convert()

	private static String get1000To999999(int nums, String numWord, Map<Integer, String> mapList) {
		
		int hundredThousandsPlace = nums / 100000;
		int tenThousandsPlace = (nums - hundredThousandsPlace * 100000) / 10000;
		int oneThousandsPlace = (nums - (tenThousandsPlace * 10000) - (hundredThousandsPlace * 100000)) / 1000;
	
		if (hundredThousandsPlace > 0) {
			numWord += mapList.get(hundredThousandsPlace) + " hundred";
			
			if (tenThousandsPlace > 0 || oneThousandsPlace > 0) {
				numWord += " and ";
			}
		}
		
		if (tenThousandsPlace > 0) {
			//need value 11 - 19
			int tempTens = (nums - hundredThousandsPlace * 100000) / 1000;
			
			if (tempTens > 19) {
				tempTens /= 10;
				tempTens *= 10;
			}
			
			numWord += mapList.get(tempTens);
			
			if (oneThousandsPlace > 0) {
				numWord += "-";
			}
		}
		
		if (oneThousandsPlace > 0) {
			numWord += mapList.get(oneThousandsPlace);
		}
		
		numWord += " thousand";
		
		if (nums % 1000 > 0) {
			numWord += " and ";
			return get1To999(nums % 1000, numWord, mapList);
		} else {
			return numWord;
		}
	}
	
	private static String get1To999(int nums, String numWord, Map<Integer, String> mapList) {

		int hundredsPlace = nums / 100; // equals 0 if 99 or less
		int tensPlace = (nums - hundredsPlace * 100) / 10;
		int onesPlace = nums % 10; // watch out for zero

		if (hundredsPlace > 0) {
			numWord += mapList.get(hundredsPlace) + " hundred";

			if (tensPlace > 0 || onesPlace > 0) {
				numWord += " and ";
			}
		}

		// check tens place
		int tempTens = nums - hundredsPlace * 100;
		if (tempTens >= 11 && tempTens <= 19) {
			return numWord += mapList.get(tempTens); // specific code for teens
		}
		if (tensPlace != 0 && onesPlace > 0) {
			tensPlace *= 10;
			numWord += mapList.get(tensPlace) + "-";
		} else if (tensPlace != 0) {
			tensPlace *= 10;
			numWord += mapList.get(tensPlace);
		}

		// check ones place
		if (onesPlace != 0) {
			numWord += mapList.get(onesPlace);
		}

		return numWord;
	}

} // close KataNumbersToWords
