package main;

import java.math.BigInteger;
import java.util.List;

public class Factor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 2) {

			System.out.println("Invalid args. Running default simulation.");
			int threadCount = 64;
			String bigInt = "99999999999999999";

			runSimulation(threadCount, new BigInteger(bigInt));

			return;
		}

		int p;
		String n;

		p = Integer.parseInt(args[0]);
		n = args[1];

		if (!validateArgs(p, n)) {
			return;
		}

		long t = getRunningtime(p, new BigInteger(n));

		System.out.println(p + "," + t + "ms.");
	}

	private static void runSimulation(int p, BigInteger n) {

		for (int i = p; 1 <= i; i -= 2) {

			long t = getRunningtime(i, n);

			System.out.println(i + "," + t + "ms.");
		}

	}

	private static long getRunningtime(int p, BigInteger n) {
		Factorizer f = new Factorizer(p, n);

		long t0 = System.currentTimeMillis();
		List<BigInteger> factors = f.getFactors();
		long t1 = System.currentTimeMillis() - t0;

		if (factors.size() == 0) {
			System.out.print("prime");
			return t1;
		}
		for (BigInteger b : factors) {
			System.out.print(b + " ");
		}

		System.out.println();

		return t1;
	}

	public static boolean validateArgs(int p, String n) {

		boolean valid = true;

		// Checks the number of threads to spawn.
		if (!(1 <= p && p < 128)) {

			System.err.println("Error: p should be between 1 and 128.");
			valid = false;
		}

		// Checks that n is a valid BigInteger.
		try {
			new BigInteger(n);

		} catch (NumberFormatException e) {
			System.err.println("Error: n is not a valid big integer.");
			valid = false;
		}

		return valid;
	}

}
