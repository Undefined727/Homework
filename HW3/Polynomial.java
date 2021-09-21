package edu.miracosta.cs113;

import java.util.LinkedList;

public class Polynomial {
	
	LinkedList<Term> terms = new LinkedList<Term>();
	int numTerms = 0;
	
	public Polynomial() {}
	
	public Polynomial(Polynomial poly) {
		this.terms = poly.terms;
		this.numTerms = poly.numTerms;
	}

	public int getNumTerms() {
		return numTerms;
	}

	public Term getTerm(int i) {
		return terms.get(i);
	}

	public void addTerm(Term t) {
		for(int i = 0; i < numTerms; i++) {
			if (t.getExponent() == terms.get(i).exponent) {
				terms.get(i).setCoefficient(terms.get(i).getCoefficient()+t.getCoefficient());
				numTerms++;
				return;
			}
		}
		
		terms.add(t);
		numTerms++;
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

}
