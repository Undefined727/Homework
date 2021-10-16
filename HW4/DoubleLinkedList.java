package edu.miracosta.cs113;
import java.util.*;

public class DoubleLinkedList<E> extends AbstractSequentialList<E>
{  // Data fields
    	private Node<E> head = null;   // points to the head of the list
    	private Node<E> tail = null;   //points to the tail of the list
    	private int size = 0;    // the number of items in the list
  
  public void add(int index, E obj) { 
	  listIterator(index).add(obj);
   }
  public void addFirst(E obj) { // Fill Here 
	  add(0, obj); 
  }
  public void addLast(E obj) { // Fill Here
	  add(size, obj);
  }

  public E get(int index) 
  { 	ListIterator<E> iter = listIterator(index); 
  try {
      	return iter.next();
  }
  catch (NoSuchElementException e) {
	  throw new IndexOutOfBoundsException();
  }
  }  
  public E getFirst() { return head.data;  }
  public E getLast() { return tail.data;  }

  public int size() {  return size;  } // Fill Here

  public int lastIndexOf(Object o) {
	  int counter = 0;
	  int indexOf = -1;
	  ListIterator iter = listIterator(0);
	  while (iter.hasNext()) {
		  if (counter > indexOf && iter.next().equals(o)) indexOf = counter;
		  counter++;
	  }
	  return indexOf;
  }
  
  public int indexOf(Object o) {
	  int counter = 0;
	  ListIterator iter = listIterator(0);
	  while (iter.hasNext()) {
		  if (iter.next().equals(o)) return counter;
		  counter++;
	  }
	  return -1;
  }
  
  public E remove(int index)
  {     E returnValue = null;
        ListIterator<E> iter = listIterator(index);
        if (iter.hasNext())
        {   returnValue = iter.next();
            iter.remove();
        }
        else {   throw new IndexOutOfBoundsException();  }
        return returnValue;
  }
  
  public E remove() {
	  return remove(0);
  }
  
  public void clear() {
	  ListIterator iter = listIterator(0);
	  while (iter.hasNext()) {
		  iter.next();
		  iter.remove();
	  }
  }
  
  public boolean contains(Object o) {
	  return indexOf(o) != -1;
  }
  
  public boolean isEmpty() {
	  return size == 0;
  }
  
  public boolean equals(Object o) {
	  return o.toString().equals(toString());
  }

  public Iterator iterator() { 
	  Iterator iter = new ListIter(0);
	  return iter;
	  }
  public ListIterator listIterator() { 
	  ListIter iter = new ListIter(0);
	  return iter;
	  }
  public ListIterator listIterator(int index) { 
	  ListIter iter = new ListIter(index);
	  return iter;
	  }
  public ListIterator listIterator(ListIterator iter)
  {     return new ListIter( (ListIter) iter);  }

  // Inner Classes
  private static class Node<E>
  {     private E data;
        private Node<E> next = null;
        private Node<E> prev = null;

        private Node(E dataItem)  //constructor
        {   data = dataItem;   }
  }  // end class Node

  public class ListIter implements ListIterator<E> 
  {
        private Node<E> nextItem;      // the current node
        private Node<E> lastItemReturned;   // the previous node
        private int index = 0;   // 

    public ListIter(int i)  // constructor for ListIter class
    {   if (i < 0 || i > size)
        {     throw new IndexOutOfBoundsException("Invalid index " + i); }
        lastItemReturned = null;
 
        if (i == size)     // Special case of last item
        {     index = size;     nextItem = null;      }
        else          // start at the beginning
        {   nextItem = head;
            for (index = 0; index < i; index++)  nextItem = nextItem.next;   
        }// end else
    }  // end constructor

    public ListIter(ListIter other)
    {   nextItem = other.nextItem;
        index = other.index;    }

    public boolean hasNext() {
    	return (nextItem != null);
    }
    public boolean hasPrevious() {
    	if (nextItem == null && size != 0) return true;
    	else if (nextItem == null) return false;
    	return (nextItem.prev != null);
    }
    public int previousIndex() {  
    	return index-1;
    }
    public int nextIndex() {
    	return index;
    	}
    public void set(E o)  {
    	if (lastItemReturned != null) lastItemReturned.data = o;
    	else throw new IllegalStateException();
    }
    public void remove(){
    	if (lastItemReturned != null) {
    		if (lastItemReturned.prev != null) {
    		lastItemReturned.prev.next = lastItemReturned.next;
    		}
    		else head = lastItemReturned.next;
    		if (lastItemReturned.next != null) {
    		lastItemReturned.next.prev = lastItemReturned.prev;
    		}
    		else tail = lastItemReturned.prev;
    		lastItemReturned = null;
    		size--;
    	}
    	else throw new IllegalStateException();
    }

    public E next()
    {  
        if (hasNext()) {
        	lastItemReturned = nextItem;
        	nextItem = nextItem.next; 
        	index++; 
        	return lastItemReturned.data;
        }
        else throw new NoSuchElementException();
    }

    public E previous() 
    {  
    	if (hasPrevious()) {
    		if (nextItem == null) nextItem = tail;
    		else {
    			nextItem = nextItem.prev;
    		}
    	lastItemReturned = nextItem; 
    	index--; 
    	return lastItemReturned.data;
    }
    else throw new NoSuchElementException(); 
    }

    public void add(E obj) {
    	if (head == null) {
    		head = new Node<E>(obj);
    		tail = head;
    	}
    	else if (nextItem == head) {
    		Node<E> newNode = new Node<E>(obj);
    		newNode.next = nextItem;
    		nextItem.prev = newNode;
    		head = newNode;
    	} else if (nextItem == null) {
    		Node<E> newNode = new Node<E>(obj);
    		tail.next = newNode;
    		newNode.prev = tail;
    		tail = newNode;
    	} else {
    		Node<E> newNode = new Node<E>(obj);
    		newNode.prev = nextItem.prev;
    		newNode.prev.next = newNode;
    		newNode.next = nextItem;
    		nextItem.prev = newNode;
    	}
    	size++;
    }
  }// end of inner class ListIter
}// end of class DoubleLinkedList
