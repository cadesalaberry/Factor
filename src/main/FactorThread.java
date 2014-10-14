package main;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FactorThread implements Runnable {

	private BigInteger number;
	private BigInteger current, step, max;
	int id, threadCount;

	private List<BigInteger> factors = new ArrayList<BigInteger>();

	public FactorThread(BigInteger n, BigInteger max, int id, int threadCount) {
		this.threadCount = threadCount;
		this.current = BigInteger.valueOf(id + 2);
		this.number = n;
		this.max = max;
		this.id = id + 2;

		step = BigInteger.valueOf(threadCount);
	}

	public void split() {

	}

	/**
	 * With an id of zero and 4 threads, it will go through all multiples of 4,
	 * starting at zero.
	 * 
	 * @return
	 */
	private BigInteger next() {

		return current.add(step);
	}

	@Override
	public void run() {
		
		while (current.compareTo(max) == -1) {
			if (number.mod(current).equals(BigInteger.ZERO)) {
				factors.add(current);
				//System.out.println(Thread.currentThread().getId() + ":" + current);
			}
			current = this.next();
		}
	}
	
	public List<BigInteger> getFactors() {
		return factors;
	}
}
