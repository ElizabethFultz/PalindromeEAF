import java.util.Scanner;
import java.io.*;

/**
 * This class accepted user and file input for sequences and then determines if the first sequence is a subsequence of another using a Stack.
 * 
 * @author Elizabeth Fultz
 * @version 2.0
 * Subsequences Project
 * Fall 2019
 */
public class Application {
	
	static Scanner scan = new Scanner(System.in); //the scanner used to take user input

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.print("Would you like to enter the sequences manually or from a file? (M/F): ");
		String sequenceMethod = scan.nextLine(); //holds the user input for further evaluation for manual or file input for sequences
		
		if(sequenceMethod.equals("M") || sequenceMethod.equals("m")) {
			sequenceManual();			
		}	
		else if(sequenceMethod.equals("F") || sequenceMethod.equals("f")) {
			sequenceFile();
		}
		
		System.out.println("\n<END RUN>");

	}//end main
	
	/**
	 * This method takes user inputed sequences and compares them
	 */
	public static void sequenceManual() {
		
		ArrayStack<Character> stackFirst = new ArrayStack<Character>(); //the stack that holds the first sequence
		ArrayStack<Character> stackSecond = new ArrayStack<Character>(); //the stack that hold the second sequence
		String again; //will be used to determine if the user would like to enter more sequences
		
		stackFirst.clear();
		stackSecond.clear();
		
		System.out.print("\nEnter the first sequence: ");
		String firstSequence = scan.nextLine(); //this holds the first user inputed sequence
		firstSequence = firstSequence.toLowerCase();
		
		for(int i = 0; i < firstSequence.length(); i++) {
			
			char letter = firstSequence.charAt(i); //used as a place holder for each letter as it is pushed to the stack
			stackFirst.push(letter);
			
		}//end push for
		
		System.out.print("Enter the second sequence: ");
		String secondSequence = scan.nextLine(); //this hold the second user inputed sequence
		secondSequence = secondSequence.toLowerCase();
		
		
		for(int i = 0; i < secondSequence.length(); i++) {
			
			char letter = secondSequence.charAt(i); //used as a place holder for each letter as it is pushed to the stack
			stackSecond.push(letter);
			
		}//end push for
		
		while(!stackSecond.isEmpty() && !stackFirst.isEmpty()) {

			char letterSecond = stackSecond.pop(); //holds the letters popped from the second sequence stack
			
			if(compare(stackFirst.peek(), letterSecond) && !stackSecond.isEmpty()) {
				stackFirst.pop();

			}
			
		}//end while loop
		
		if(stackFirst.isEmpty())
			System.out.println("\n" + firstSequence + " IS A SUBSEQUENCE of " + secondSequence);
		else
			System.out.println("\n" + firstSequence + " IS NOT A SUBSEQUENCE of " + secondSequence);
		
		System.out.print("\nWould you like to enter more sequences? (Y/N): ");
		again = scan.nextLine();
		
		if(again.equals("y") || again.equals("Y")) {
			
			sequenceManual();
			
		}// end recursive for	
		
	}//end Manual
	
	/**
	 * This method reads sequences from a file and compares them
	 */
	public static void sequenceFile() {
		
		ArrayStack<Character> stackFirst = new ArrayStack<Character>(); //the stack that holds the first sequence
		ArrayStack<Character> stackSecond = new ArrayStack<Character>(); //the stack that holds the second sequence
		
		System.out.print("\nEnter the name of the file to process: ");
		String fileName = scan.nextLine(); //holds the file name inputed by the user
		
		File file = new File(fileName);
		
		try {
			Scanner fileScanner = new Scanner(file);
			fileScanner.useDelimiter(",");
			
			while(fileScanner.hasNext()) {
				
				String sequenceFirst = ""; //Initializes and clears the sequenceFirst variable that will hold the first read sequence
				String sequenceSecond = ""; //Initializes and clears the sequenceSecond variable that will hold the second read sequence
				
				stackFirst.clear();
				stackSecond.clear();
				
				String line = fileScanner.nextLine();
				
				Scanner lineScanner = new Scanner(line);
				lineScanner.useDelimiter(",");
				
				sequenceFirst = lineScanner.next();
				sequenceFirst = sequenceFirst.toLowerCase();			
	
				for(int i = 0; i < sequenceFirst.length(); i++) {
					
					char letter = sequenceFirst.charAt(i); //used as a place holder for each letter as it is pushed to the stack
					stackFirst.push(letter);
					
				}//end first push for
				
				sequenceSecond = lineScanner.next();
				sequenceSecond = sequenceSecond.toLowerCase();
				
				for(int i = 0; i < sequenceSecond.length(); i++) {
					
					char letter = sequenceSecond.charAt(i); //used as a place holder for each letter as it is pushed to the stack
					stackSecond.push(letter);
					
				}//end second push for
				
				try {
				
					while(!stackSecond.isEmpty() && !stackFirst.isEmpty()) {
						
						char letterSecond = stackSecond.pop(); //holds the letters popped from the second sequence stack
						
						while(compare(stackFirst.peek(), letterSecond) && !stackSecond.isEmpty()) {
							stackFirst.pop();
						}
						
					}//end while loop
					
				} //end try
				
				catch(java.util.EmptyStackException ignore) {}
				
				if(stackFirst.isEmpty())
					System.out.println(sequenceFirst + " IS A SUBSEQUENCE of " + sequenceSecond);
				else
					System.out.println(sequenceFirst + " IS NOT A SUBSEQUENCE of " + sequenceSecond);
			
			}//end scanner while
			
		}//end try
		
		catch(IOException ex) {
			
			System.out.println("File not Found");
			
		}//end catch
			
	}//end File
	
	/**
	 * This method takes two characters and compares them
	 * 
	 * @param one the first character to compare
	 * @param two the second character to compare
	 * @return true if the characters are the same and false if they are different
	 */
	public static boolean compare(char one, char two) {
		
		if(two == one)
			return true;
		else
			return false;
		
	}//end compare

}//end class
