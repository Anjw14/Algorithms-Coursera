package assignment_2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private Node first, last;
	private int n;
	
    // helper linked list class
    private class Node {
        private Item item;
        private Node next, prior;
    }
    public Deque(){
    	// construct an empty deque
    	first = null;
    	last = null;
    	n = 0;
    }
    public boolean isEmpty(){
    	// is the deque empty?
    	return first == null;
    }
    public int size(){
    	// return the number of items on the deque
    	return n;
    }
    public void addFirst(Item item){
    	// add the item to the front
    	if(item==null)
    		throw new IllegalArgumentException();
    	else{
    		Node oldFirst = first;
    		first = new Node();
    		first.item = item;
    		first.prior = null;
    		if(last == null){
    			first.next = null;
    			last = first;
    		}
    		else{
    			first.next = oldFirst;
    			oldFirst.prior = first;
    		}
    		n++;
    	}
    }
    public void addLast(Item item){
    	// add the item to the end
    	if(item==null)
    		throw new IllegalArgumentException();
    	else{
	    	Node oldLast = last;
	    	last = new Node();
	    	last.item = item;
	    	last.next = null;
	    	if(isEmpty()){
	    		last.prior = null;
	    		first = last;
	    	}
	    	else{
	    		last.prior = oldLast;
	    		oldLast.next = last;
	    	}
	    	n++;
    	}
    }
    public Item removeFirst(){
    	// remove and return the item from the front
    	if(isEmpty())	throw new NoSuchElementException("NoSuchElementException");
    	Item item = first.item;
    	first = first.next;
    	first.prior = null;
    	n--;
		return item;
    }
    public Item removeLast(){
    	// remove and return the item from the end
    	if(isEmpty())	throw new NoSuchElementException("NoSuchElementException");
    	Item item = last.item;
    	last = last.prior;
    	last.next = null;
    	n--;
		return item;
    }
    public Iterator<Item> iterator(){
    	// return an iterator over items in order from front to end
    	return new DequeIterator();
    }
    private class DequeIterator implements Iterator<Item>{
    	
    	private Node current = first;
    	
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if(!hasNext())
				throw new NoSuchElementException();  
			else{
				Item item = current.item;
				current = current.next; 
				return item;
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();  
		}
    }
    public static void main(String[] args){
    	// unit testing (optional)
    	Deque<Integer> test = new Deque<Integer>();
    	System.out.println(test.first+","+test.last+","+test.n);
    	test.addFirst(1);
    	test.addLast(5);
    	test.addFirst(3);
    	test.addLast(8);
    	System.out.println(test.first.item+","+test.last.item+","+test.n);
    	for (Iterator<Integer> iter = test.iterator(); iter.hasNext();) {
    		System.out.println(iter.next());
    	}
    	test.removeFirst();
    	test.removeLast();
    	System.out.println(test.first.item+","+test.last.item+","+test.n);
    	for (Iterator<Integer> iter = test.iterator(); iter.hasNext();) {
    		System.out.println(iter.next());
    	}
    }
}