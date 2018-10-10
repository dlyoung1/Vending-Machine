package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;

public class FindAndReplace {

	public static void main(String[] args) {

		// Write a program that prompts the user for a word
		// and searches a file for that word
		// prints out lines with the word
		// /Users/mnachman/repos/team-exercises/team3-java-week4-pair-exercises/file-io-part2-exercises/alices_adventures_in_wonderland.txt

		Scanner userInput = new Scanner(System.in);

		String searchWord = getSearchWordFromUser(userInput);

		String replaceWord = getReplaceWordFromUser(userInput);

		File inputFile = getInputFileFromUser(userInput);

		File outputFile = getDestinationFileFromUser(userInput);

		searchAndReplaceTextFile(searchWord, replaceWord, inputFile, outputFile);

		userInput.close(); // only close when everything else is absolutely done or ERRORS
							// when user is ready to quit in a larger program

	} // close main

	private static String getSearchWordFromUser(Scanner userInput) {
		String searchWord;

		System.out.print("Enter search word: ");
		searchWord = userInput.nextLine();

		// userInput.close();

		return searchWord;
	} // close getSearchWordFromuser()

	private static String getReplaceWordFromUser(Scanner userInput) {
		String replacehWord;

		System.out.print("Enter replacement word: ");
		replacehWord = userInput.nextLine();

		// userInput.close();

		return replacehWord;
	} // close getReplaceWordFromUser

	private static File getInputFileFromUser(Scanner userInput) {
		File inputFile = null;

		System.out.print("Enter input file path: ");
		String path = userInput.nextLine();

		inputFile = new File(path);

		// error checking
		if (!inputFile.exists()) {
			System.out.println(path + " does not exist");
			System.exit(1);
		} else if (!inputFile.isFile()) {
			System.out.println(path + " is not a file");
			System.exit(1);
		}

		return inputFile;
	} // close getInputFileFromUser()

	private static File getDestinationFileFromUser(Scanner userInput) {
		File outputFile = null;

		System.out.print("Enter destination file path and name: ");
		String path = userInput.nextLine();

		outputFile = new File(path);

		try {
			outputFile.createNewFile();
		} catch (IOException e) {
			System.out.println(path + " is not a valid file");
			e.printStackTrace();
			System.exit(1);
		}

		return outputFile;
	}

	private static void searchAndReplaceTextFile(String searchWord, String replaceWord, File inputFile,
			File outputFile) {

		try (Scanner fileScanner = new Scanner(inputFile)) {
			PrintWriter outputWriter = new PrintWriter(outputFile);

			while (fileScanner.hasNextLine()) {

				String line = fileScanner.nextLine();

				if (line.contains(searchWord)) {
					line = line.replaceAll(searchWord, replaceWord);
				}

				outputWriter.println(line);
			}

			outputWriter.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} // close searchAndReplaceTextFile()

}
