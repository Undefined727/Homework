package edu.miracosta.cs113;

public class Term implements Comparable<Term> {
	
	int coefficient = 1;
	int exponent = 1;
	
	public Term() {}
	
	public Term(int a, int b) {
		coefficient = a;
		exponent = b;
	}
	
	public Term(Term term) {
		coefficient = term.getCoefficient();
		exponent = term.getExponent();
	}
	
	public Term(String term) {
		int offset = 0;
		try {
		if (term.equals("")) throw new Exception("Tried to create Term from empty string.");
		
		
		if (term.charAt(0) != '+' && term.charAt(1) != 'x') {
			if(term.length()  < 3) coefficient = Character.getNumericValue(term.charAt(1)) * -1;
			else if(term.charAt(2) == 'x') coefficient = Character.getNumericValue(term.charAt(1)) * -1;
			else {
				coefficient = Character.getNumericValue(term.charAt(1)) * -10 - Character.getNumericValue(term.charAt(2));
				offset--;
			}
		}
		else if (term.charAt(1) != 'x') {
			if(term.length()  < 3) coefficient = Character.getNumericValue(term.charAt(1));
			else if(term.charAt(2) == 'x') coefficient = Character.getNumericValue(term.charAt(1));
			else {
				coefficient = Character.getNumericValue(term.charAt(1)) * 10 + Character.getNumericValue(term.charAt(2));
				offset--;
			}
		}
		else {
			if (term.charAt(0) == '+') coefficient = 1;
			else coefficient = -1;
			offset++;
		}
		

		if (term.length()+offset > 3) {
			if (term.charAt(4-offset) != '-') {
				if (term.length()+offset > 5) exponent = Character.getNumericValue(term.charAt(4-offset)) * 10 + Character.getNumericValue(term.charAt(5-offset));
				else exponent = Character.getNumericValue(term.charAt(4-offset));
			}
			else {
				if (term.length()+offset > 6) exponent = Character.getNumericValue(term.charAt(5-offset)) * -10 - Character.getNumericValue(term.charAt(6-offset));
				else exponent = Character.getNumericValue(term.charAt(5-offset)) * -1;
			}
		}
		else if (term.length()+offset > 2) exponent = 1;
		else exponent = 0;
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void setCoefficient(int a) {coefficient = a;}
	
	public void setExponent(int a) {exponent = a;}
	
	public void setAll(int a, int b) {
		coefficient = a;
		exponent = b;
	}
	
	public int getCoefficient() {return coefficient;}
	
	public int getExponent() {return exponent;}
	
	
	public Term clone() {
		return new Term(coefficient, exponent);
	}
	
	public int compareTo(Term term) {
		return exponent - (int) term.getExponent();
	}
	
	public boolean equals(Object obj) {
		return (toString().equals(obj.toString()));
	}
	
	public String toString() {
		String returned = "";
		if (coefficient == 0) return "";
		else if (coefficient > 1) returned += "+" + coefficient;
		else if (coefficient == 1) returned += "+";
		else if (coefficient == -1) returned += "-";
		else returned += coefficient;
		if (exponent > 1 || exponent < 0) returned += "x^" + exponent + "";
		else if (exponent == 1) returned += "x";
		else returned += "";
		return returned;
	}
}
