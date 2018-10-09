package com.techelevator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class WordCount {

	public static void main(String[] args) {

		///Users/mnachman/repos/exercises/m1-w4d2-file-io-part1-exercises/alices_adventures_in_wonderland.txt
		
		//Task:
		//read in file
		//count number of words
		//count number of sentences (. or ! or ?)
		
		//count number of spaces?
		//count number of . or ! or ?
		
		Scanner userInput = new Scanner(System.in);
		
		File inputFile = getInputFile(userInput);
				//new File("alices_adventures_in_wonderland.txt");
		
		
		
		wordCounter(inputFile);
		
		userInput.close();
		
	}	//close main
	
	private static File getInputFile(Scanner userInput) {
		File inputFile = null;
		
		System.out.print("Please enter the file path of the .txt file to scan: ");
		String path = userInput.nextLine();
		
		inputFile = new File(path);
		
		return inputFile;
	}
	
	private static void wordCounter(File inputFile) {
		
		try (Scanner fileScanner = new Scanner(inputFile)) {
			
			int wordCount = 0;
			int sentenceCount = 0;
			
			while(fileScanner.hasNextLine()) {
				
				String line = fileScanner.nextLine();
				
				line = line.trim();		//eliminate some junk
				
				String[] tempWordArray = line.split("[\n ]+");
				
				for (int i = 0; i < tempWordArray.length; i++) {
					if(!tempWordArray[i].isEmpty()) {
					//System.out.println(tempWordArray[i]);
					wordCount++;
					}
				}
				
				if (line.contains(".") || line.contains("!") || line.contains("?")) {
					sentenceCount++;
				}
				
				
			}
			
			System.out.println("Word Count: " + wordCount);
			System.out.println("Sentence Count: " + sentenceCount);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("You blew it up!");
			e.printStackTrace();
		}
		
	}

}	//close WordCount
