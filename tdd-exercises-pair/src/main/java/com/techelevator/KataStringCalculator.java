package com.techelevator;

public class KataStringCalculator {
	
	public static int add(String nums) {
		
		int result = 0;
		String delimeter = "\n,";
		
		if(nums.equals("")) {
			return result;
		} else {
			//check if user is passing custom delimeters
			if(nums.contains("//")) {
				int firstIndex = nums.indexOf("//") + 2;
				int secondIndex = nums.indexOf("\n");
				
				delimeter += nums.substring(firstIndex, secondIndex);
				
				//remove garbage at the front of the string
				nums = nums.substring(secondIndex+1);
			}			
			String splitNums[] = nums.split("[" + delimeter + "]");
			
			for(int i = 0; i < splitNums.length; i++) {
				result += Integer.parseInt(splitNums[i]);
			}			
		}		
		return result;
	}	//close add()

}	//close KataStringCalculator
