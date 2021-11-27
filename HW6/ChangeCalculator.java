package edu.miracosta.cs113.change;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * ChangeCalculator : Class containing the recursive method calculateChange, which determines and prints all
 * possible coin combinations representing a given monetary value in cents.
 *
 * Problem derived from Koffman & Wolfgang's Data Structures: Abstraction and Design Using Java (2nd ed.):
 * Ch. 5, Programming Project #7, pg. 291.
 *
 * NOTE: An additional method, printCombinationsToFile(int), has been added for the equivalent tester file to
 * verify that all given coin combinations are unique.
 */
public class ChangeCalculator {

	static LinkedList<Combination> list = new LinkedList<>();
    /**
     * Wrapper method for determining all possible unique combinations of quarters, dimes, nickels, and pennies that
     * equal the given monetary value in cents.
     *
     * In addition to returning the number of unique combinations, this method will print out each combination to the
     * console. The format of naming each combination is up to the user, as long as they adhere to the expectation
     * that the coins are listed in descending order of their value (quarters, dimes, nickels, then pennies). Examples
     * include "1Q 2D 3N 4P", and "[1, 2, 3, 4]".
     *
     * @param cents a monetary value in cents
     * @return the total number of unique combinations of coins of which the given value is comprised
     */
    public static int calculateChange(int cents) {
        // TODO:
        // Implement a recursive solution following the given documentation.
    	list.clear();
    	//System.out.println("Testing for " + cents + ".");
    	
    	int returned = calculateChange(cents, 0, 0, 0);
    	//System.out.println(returned + " total combinations.");
    	
    	return returned;
    }
    
    public static int calculateChange(int cents, int quarters, int dimes, int nickels) {
    	boolean hasDuplicate = false;
    	Combination newCombination = new Combination(quarters, dimes, nickels, cents);
    	for(int i = 0; i < list.size(); i++) {
			if (newCombination.equals(list.get(i))) hasDuplicate = true;
		}
    	int combinations = 1;
    	
    	if (cents >= 25) {
    		combinations += calculateChange(cents-25, quarters+1, dimes, nickels);
    	}
    	if (cents >= 10) {
    		combinations += calculateChange(cents-10, quarters, dimes+1, nickels);
    	}
    	if (cents >= 5) {
    		combinations += calculateChange(cents-5, quarters, dimes, nickels+1);
    	}
    	if (cents < 5) {
    		if (!hasDuplicate) {
    		//System.out.println(quarters + " quarters, " + dimes + " dimes, " + nickels + " nickels, and " + cents + " pennies.");
    		list.add(newCombination);
    		return 1;
    		}
    		else return 0;
    	}
    	
    	if (!hasDuplicate) {
    		//System.out.println(quarters + " quarters, " + dimes + " dimes, " + nickels + " nickels, and " + cents + " pennies.");
    		list.add(newCombination);
    		return combinations;
    		}
    		else return 0;
    }
    
    static class Combination {
    	int quarters = 0;
    	int dimes = 0;
    	int nickels = 0;
    	int pennies = 0;
    	public Combination(int quarters, int dimes, int nickels, int pennies) {
    		this.quarters = quarters;
    		this.dimes = dimes;
    		this.nickels = nickels;
    		this.pennies = pennies;
    	}
    	
    	public boolean equals(Combination equalsTo) {
    		return (equalsTo.quarters == this.quarters && equalsTo.dimes == this.dimes && equalsTo.nickels == this.nickels);
    	}
    }

    /**
     * Calls upon calculateChange(int) to calculate and print all possible unique combinations of quarters, dimes,
     * nickels, and pennies that equal the given value in cents.
     *
     * Similar to calculateChange's function in printing each combination to the console, this method will also
     * produce a text file named "CoinCombinations.txt", writing each combination to separate lines.
     *
     * @param cents a monetary value in cents
     */
    public static void printCombinationsToFile(int cents) {
        // TODO:
        // This when calculateChange is complete. Note that the text file must be created within this directory.
    	calculateChange(cents);
    	
        try {
        	File combinationFile = new File("src/CoinCombinations.txt");
        	combinationFile.createNewFile();
        	System.out.println(combinationFile.getAbsolutePath());
        	FileWriter myWriter = new FileWriter("CoinCombinations.txt");
        	for(int i = 0; i < list.size(); i++) {
    			myWriter.write(list.get(i).quarters + " quarters, " + list.get(i).dimes + " dimes, " + list.get(i).nickels + " nickels, " + list.get(i).pennies + " pennies.");
    		}
			myWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Program broke :(");
		}
    	
    	
    }

} // End of class ChangeCalculator