package assignment_1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	final double[] ratio;
	final int trials;
	
	public PercolationStats(int n, int trials){
		// perform trials independent experiments on an n-by-n grid
		if(n<=0 || trials<=0)
			throw new IllegalArgumentException("Caution!");
		ratio = new double[trials];
		this.trials = trials;
		for(int i=0; i<trials; i++){
			Percolation perc = new Percolation(n);
			while(!perc.percolates())
				perc.open(StdRandom.uniform(1, n+1), StdRandom.uniform(1, n+1));
			ratio[i] = perc.numberOfOpenSites() / (n*n*1.0);			// зЂвт /
		}
	}
    public double mean(){
    	// sample mean of percolation threshold
    	return StdStats.mean(ratio);
    }
    public double stddev(){
    	// sample standard deviation of percolation threshold
    	return StdStats.stddev(ratio);
    }
    public double confidenceLo(){
    	// low  endpoint of 95% confidence interval
    	return mean() - 1.96*stddev()/Math.sqrt(trials);
    }
    public double confidenceHi(){
    	// high endpoint of 95% confidence interval
    	return mean() + 1.96*stddev()/Math.sqrt(trials);
    }

    public static void main(String[] args){
    	// test client (described below)
    	PercolationStats percSta = new PercolationStats(2, 10000);
    	System.out.println(percSta.mean());
    	System.out.println(percSta.stddev());
    	System.out.println("[ "+percSta.confidenceLo()+" , "+percSta.confidenceHi()+" ]");
    }
}
