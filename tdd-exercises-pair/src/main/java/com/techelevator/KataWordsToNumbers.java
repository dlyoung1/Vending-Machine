package com.techelevator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class KataWordsToNumbers {

	public static int convertToInt(String wordNum) {

		wordNum = wordNum.toLowerCase();
		String delimeter = "[- ]+";
		int returnNum = 0;

		Map<String, Integer> numMap = new HashMap<String, Integer>();
		numMap.put("zero", 0);
		numMap.put("one", 1);
		numMap.put("two", 2);
		numMap.put("three", 3);
		numMap.put("four", 4);
		numMap.put("five", 5);
		numMap.put("six", 6);
		numMap.put("seven", 7);
		numMap.put("eight", 8);
		numMap.put("nine", 9);
		numMap.put("ten", 10);
		numMap.put("eleven", 11);
		numMap.put("twelve", 12);
		numMap.put("thirteen", 13);
		numMap.put("fourteen", 14);
		numMap.put("fifteen", 15);
		numMap.put("sixteen", 16);
		numMap.put("seventeen", 17);
		numMap.put("eighteen", 18);
		numMap.put("nineteen", 19);
		numMap.put("twenty", 20);
		numMap.put("thirty", 30);
		numMap.put("forty", 40);
		numMap.put("fifty", 50);
		numMap.put("sixty", 60);
		numMap.put("seventy", 70);
		numMap.put("eighty", 80);
		numMap.put("ninety", 90);
		numMap.put("hundred", 100);
		numMap.put("thousand", 1000);

		String[] wordNumArray = wordNum.split(delimeter);

		// for(String a : wordNumArray) {
		// System.out.println(a);
		// }

		// thousands+ method
		// calculate 1000-999,000
		// if you see "thousand" ignore any elements after it
		if (Arrays.asList(wordNumArray).contains("thousand")) {
			for (int i = 0; i < wordNumArray.length; i++) {
				// needs a contains check?
				//yes....
				if (numMap.containsKey(wordNumArray[i])) {
					if (numMap.get(wordNumArray[i]) == 1000) { // getting null exception on "and" here
						for (int j = 0; j < i; j++) {
							if (wordNumArray[j].equals("hundred")) {
								returnNum = returnNum * numMap.get(wordNumArray[j]);
								wordNumArray[j] = "scrub";
							} else {
								if (numMap.containsKey(wordNumArray[j])) {
									returnNum += numMap.get(wordNumArray[j]);
									wordNumArray[j] = "scrub";
								}
							}
						}
						returnNum = returnNum * numMap.get(wordNumArray[i]);
						wordNumArray[i] = "scrub";
					}
				}

				// wordNumArray[i] = "scrub";
			}
		}

		// one hundred twelve
		// "one" "hundred" "sixty" "five"

		for (int i = 0; i < wordNumArray.length; i++) {
			if (numMap.containsKey(wordNumArray[i])) {
				if (i + 1 < wordNumArray.length && numMap.get(wordNumArray[i + 1]) == 100) {
					returnNum += numMap.get(wordNumArray[i]) * numMap.get(wordNumArray[i + 1]);
					wordNumArray[i + 1] = "and";
				} else {
					returnNum += numMap.get(wordNumArray[i]);
				}
			}
		}

		return returnNum;
	} // close convertToInt

} // close KataWordsToNumbers
