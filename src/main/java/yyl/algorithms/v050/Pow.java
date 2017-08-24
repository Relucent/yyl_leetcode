package yyl.algorithms.v050;

/**
 * Implement pow(x, n). <br>
 */
public class Pow {

	//[34.00515,-2147483648]
	public static void main(String[] args) {
		double base = 8.88023;
		int power = 3;
		System.out.println(base + "^" + power + "=?");
		System.out.println(myPow(base, power));
		System.out.println(myPow1(base, power));
		System.out.println(myPow2(base, power));
	}

	public static double myPow(double x, int n) {
		return Math.pow(x, n);
	}

	public static double myPow1(double x, int n) {
		if (x == 1.0) {
			return x;
		}
		if (n < 0) {
			return Integer.MIN_VALUE == n //
					? 1.0 / (myPow1(x, Integer.MAX_VALUE) * x)//
					: 1.0 / myPow1(x, -n);
		}
		double r = 1.0;
		while ((n--) > 0) {
			r *= x;
		}
		return r;
	}

	// x^0b0001  -> x
	// x^0b0010  -> x^2
	// x^0b0100  -> (x^2)^2
	// x^0b0111  -> [(x^2)^2]  *  [x^2] * [x]
	// x^0b0101  -> [(x^2)^2]  *  1 * [x]
	public static double myPow2(double x, int n) {
		if (x == 1.0) {
			return x;
		}
		if (n < 0) {
			return Integer.MIN_VALUE == n //
					? 1.0 / (myPow2(x, Integer.MAX_VALUE) * x)//
					: 1.0 / myPow2(x, -n);
		}
		double r = 1.0;
		while (n != 0) {
			if ((n & 1) == 1) {
				r *= x;
			}
			x = x * x;
			n = n >> 1;
		}
		return r;
	}
}
//MIN_VALUE -2147483648
//MAX_VALUE 2147483647