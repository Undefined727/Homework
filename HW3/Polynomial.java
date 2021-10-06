package edu.miracosta.cs113;

import java.util.Collections;
import java.util.LinkedList;

public class Polynomial {
	
	LinkedList<Term> terms = new LinkedList<Term>();
	int numTerms = 0;
	
	public Polynomial() {}
	
	public Polynomial(Polynomial poly) {
		for(int i = 0; i < poly.numTerms; i++) addTerm(poly.getTerm(i));
		sort();
	}

	public int getNumTerms() {
		return numTerms;
	}

	public Term getTerm(int i) {
		return terms.get(i);
	}

	public void addTerm(Term added) {	
		Term t = new Term(added);
		for(int i = 0; i < numTerms; i++) {
			if (t.getExponent() == terms.get(i).exponent) {
				terms.get(i).setCoefficient(terms.get(i).getCoefficient()+t.getCoefficient());
				sort();
				return;
			}
		}
		terms.add(t);
		numTerms++;
		sort();
	}

	public void clear() {
		terms.clear();
		numTerms = 0;
	}

	public void add(Polynomial poly) {
		for(int i = 0; i < poly.numTerms; i++) {
			addTerm(poly.terms.get(i));
		}
		
	}
	
	public void sort() {
		for(int i = 0; i<numTerms; i++) {
			if (terms.get(i).getCoefficient() == 0) {
				terms.remove(i);
				numTerms--;
			}
		}
		Collections.sort(terms, Collections.reverseOrder());
	}

	public String toString() {
		if (numTerms == 0) return "0";
		String returnedString = "";
		for(int i = 0; i<numTerms; i++) {
			if (terms.get(i).coefficient > 0) returnedString += terms.get(i).toString().substring(1);
			else returnedString += terms.get(i).toString();
			if (i < numTerms-1 && terms.get(i+1).coefficient > 0) returnedString += "+";
		}
		return returnedString;
	}
}
