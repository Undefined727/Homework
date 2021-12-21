package edu.miracosta.cs113;

import java.util.Scanner;


public class BinaryTree<E> {
	
	Node head = new Node();
	
	class Node<E>{
		Node left = null;
		Node right = null;
		
		E value;
		
		public Node() {}
		public Node(E value) {
			this.value = value;
		}
		
		public String toString() {
			String returned = "";
			if (left != null) returned += left.toString();
			returned += value.toString();
			if (right != null) returned += right.toString();
			return returned;
		}
		
		public Node<E> getLeft() {
			return left;
		}
		
		public Node<E> getRight() {
			return right;
		}
		
		public E getData() {
			return value;
		}
		
		
		
	}

	public BinaryTree() {
		
	}
	
	protected BinaryTree(Node<E> root) {
		this.head = root;
	}
	
	public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
		this.head = new Node(data);
		this.head.left = leftTree.head;
		this.head.right = rightTree.head;
	}
	
	public BinaryTree<E> getLeftSubtree() {return new BinaryTree(head.getLeft());}
	
	public BinaryTree<E> getRightSubtree() {return new BinaryTree(head.getRight());}
	
	public boolean isLeaf() {return (head.getLeft() == null && head.getRight() == null);}
	
	public String toString() {return head.toString();}
	
	public static BinaryTree<String> readBinaryTree(Scanner scan) {
		return null;
	}
	
	public boolean equals(Object anObject) {return toString().equals(anObject.toString());}
	
	public E getData() {return (E) head.getData();}
}
