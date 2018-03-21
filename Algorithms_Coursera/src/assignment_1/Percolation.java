package assignment_1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private int[][] grid;
	final int size;
	private int openNum;
	final WeightedQuickUnionUF grid_connectivity;
	public Percolation(int n){
		// create n-by-n grid, with all sites blocked
		if(n <= 0)
			throw new IllegalArgumentException("wrong size");
		grid = new int[n][n];
		size = n;
		openNum = 0;
		grid_connectivity = new WeightedQuickUnionUF(n*n+2);
		
	}
	private void checkIndex(int i, int j){
   		if (i < 1 || i > size)
   			throw new IndexOutOfBoundsException("row index i out of bounds");
   		if (j < 1 || j > size)
   			throw new IndexOutOfBoundsException("column index j out of bounds");
   	}
	public void open(int row, int col){
		// open site (row, col) if it is not open already
		checkIndex(row, col);
		if(!isOpen(row,col)){
			openNum += 1;
			grid[row-1][col-1] = 1;
			if(row>1 && isOpen(row-1,col))	grid_connectivity.union((row-2)*size+col, (row-1)*size+col);
			if(col>1 && isOpen(row,col-1))	grid_connectivity.union((row-1)*size+col-1, (row-1)*size+col);
			if(row<size && isOpen(row+1,col))	grid_connectivity.union((row-1)*size+col, row*size+col);
			if(col<size && isOpen(row,col+1))	grid_connectivity.union((row-1)*size+col+1, (row-1)*size+col);
			if(row == 1)	grid_connectivity.union(0, col);
			if(row == size)	grid_connectivity.union(size*size+1, (row-1)*size+col);
		}
	}
	public boolean isOpen(int row, int col){
		// is site (row, col) open?
		checkIndex(row, col);
		if(grid[row-1][col-1] == 1)		
			return true;
		return false;
	}
	public boolean isFull(int row, int col){
		// is site (row, col) full?
		checkIndex(row, col);
		if(grid_connectivity.connected(0, (row-1)*size+col))
			return true;
		else
			return false;
	}
	public int numberOfOpenSites(){
		// number of open sites
		return openNum;
	}
	public boolean percolates(){
		// does the system percolate?
		if(grid_connectivity.connected(0, size*size+1))
			return true;
		return false;
	}

	public static void main(String[] args){
		// test client (optional)
	}
}
