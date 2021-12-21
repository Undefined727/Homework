package edu.miracosta.cs113;
import java.io.File;
import java.util.Scanner;



/**
 * MorseCodeTree : A BinaryTree, with Nodes of type Character to represent each letter of the English alphabet,
 * and a means of traversal to be used to decipher Morse code.
 *
 * @version 1.0
 */
public class MorseCodeTree extends BinaryTree{
	
	
	MorseCodeTree() {}
	
    // TODO:
    // Build this class, which includes the parent BinaryTree implementation in addition to
    // the `translateFromMorseCode` and `readMorseCodeTree` methods. Documentation has been suggested for the former,
    // where said exceptional cases are to be handled according to the corresponding unit tests.

    /**
     * Non-recursive method for translating a String comprised of morse code values through traversals
     * in the MorseCodeTree.
     *
     * The given input is expected to contain morse code values, with '*' for dots and '-' for dashes, representing
     * only letters in the English alphabet.
     *
     * This method will also handle exceptional cases, namely if a given token's length exceeds that of the tree's
     * number of possible traversals, or if the given token contains a character that is neither '*' nor '-'.
     *
     * @param morseCode The given input representing letters in Morse code
     * @return a String representing the decoded values from morseCode
     */
    public String translateFromMorseCode(String morseCode) {
    	readMorseCodeTree();
        String[] individualLetters = morseCode.split(" ");
        String submitted = "";
        
        Node current = new Node();
        for(int i = 0; i < individualLetters.length; i++) {
        	current = head;
        	while(individualLetters[i].length() > 0) {
        		if (individualLetters[i].charAt(0) == '*') {
        			individualLetters[i] = individualLetters[i].substring(1);
        			current = current.left;
        		}
        		else {
        			individualLetters[i] = individualLetters[i].substring(1);
        			current = current.right;
        		}
        	}
        	submitted += current.value;
        }
        return submitted;
    }
    
    
    public String readMorseCodeTree() {
    	try {
    	File inputFile = new File("code.txt");
    	Scanner inputScan = new Scanner(inputFile);
    	
    	Node current = new Node();
    	while (inputScan.hasNextLine()) {
    		String line = inputScan.nextLine();
    		char addedChar = line.charAt(0);
    		line = line.substring(2);
    		
    		current = head;
    		while(line.length() > 0) {
    			if (line.charAt(0) == '*') {
    				if (current.getLeft() == null) current.left = new Node();
    				current = current.getLeft();
    			}
    			else {
    				if (current.getRight() == null) current.right = new Node();
    				current = current.getRight();
    			}
    			line = line.substring(1);
    		}
    		current.value = addedChar;
    		
    	}
    	return "success";
    	}
    	catch (Exception e) {e.printStackTrace();}
    	return "fail";
    	
    	
    }

} // End of class MorseCodeTree