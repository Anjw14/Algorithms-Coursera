package assignment_2;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private Item[] a;
	private int n;
	
	public RandomizedQueue(){
		// construct an empty randomized queue
		a = (Item[]) new Object[2];
		n = 0;
	}
	public boolean isEmpty(){
		// is the randomized queue empty?
		return n == 0;
	}
	public int size(){
		// return the number of items on the randomized queue
		return n;
	}
    // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= n;
        // textbook implementation
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }
	public void enqueue(Item item){
		// add the item
    	if(item==null)
    		throw new IllegalArgumentException();
    	else{
			if (n == a.length) resize(2*a.length);    // double size of array if necessary
	        a[n++] = item;                            // add item
    	}
	}
	public Item dequeue(){
		// remove and return a random item
        if (isEmpty()) throw new NoSuchElementException();
        int idx = StdRandom.uniform(n);
        Item item = a[idx];
        a[idx] = a[n-1];						  // we can change the order.
        a[n-1] = null;
        n--;
        if (n > 0 && n == a.length / 4) resize(a.length / 2);
        return item;
	}
	public Item sample(){
		// return a random item (but do not remove it)
        if (isEmpty()) throw new NoSuchElementException();
		return a[StdRandom.uniform(n)];
	}
	public Iterator<Item> iterator(){
		// return an independent iterator over items in random order
		return new RdmQueIterator();
	}
	private class RdmQueIterator implements Iterator<Item>{
		private Item[] aRand;
		private int count;
		public RdmQueIterator(){
			aRand = (Item[]) new Object[n];
			//System.arraycopy(a, 0, aRand, 0, a.length);	//can not use it
			for (int i=0; i<n; i++)
				aRand[i] = a[i];
			StdRandom.shuffle(aRand);
			count =0;
		}
		@Override
		public boolean hasNext() {
			return count < n;
		}

		@Override
		public Item next() {
			if(!hasNext())
				throw new NoSuchElementException();  
			else
				return aRand[count++];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();  
		}
	}
	public static void main(String[] args){
		// unit testing (optional)
		RandomizedQueue<Integer> test = new RandomizedQueue<Integer>();
		test.enqueue(1);
		test.enqueue(2);
		test.enqueue(3);
		test.enqueue(4);
		test.enqueue(5);
		for (Iterator<Integer> iter = test.iterator(); iter.hasNext();) {
			System.out.println(iter.next());
		}
		test.dequeue();
		test.dequeue();
		for (Iterator<Integer> iter = test.iterator(); iter.hasNext();) {
			System.out.println(iter.next());
		}
	}
}
