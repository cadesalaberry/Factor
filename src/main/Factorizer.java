package main;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Factorizer {

	private int p;
	private BigInteger n, max;

	final static BigInteger TWO = BigInteger.valueOf(2);

	public Factorizer(int p, BigInteger n) {
		this.p = p;
		this.n = n;

		/**
		 * Square root has 2 times less digits as original value we can start
		 * with 2^(n.length() / 2)
		 */
		this.max = TWO.pow(n.bitLength() / 2);
	}

	public List<BigInteger> getFactors() {

		List<FactorThread> factorizers = new ArrayList<FactorThread>();
		List<Thread> threads = new ArrayList<>();

		for (int i = 0; i < p; i++) {
			
			FactorThread f = new FactorThread(n, max, i, p);
			factorizers.add(f);

			Thread t = new Thread(f);
			threads.add(t);
			t.start();
		}

		for (Thread t : threads) {

			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		List<BigInteger> factors = new ArrayList<>();

		for (FactorThread fz : factorizers) {
			factors.addAll(fz.getFactors());
		}

		Collections.sort(factors);

		return factors;
	}
}
