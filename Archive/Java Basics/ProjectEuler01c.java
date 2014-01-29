/**
 * This is a sample solution for Project Euler problem #1, posted at:
 *
 *     http://projecteuler.net/problem=1
 *
 * The problem states:
 *
 *     "If we list all the natural numbers below 10 that are multiples of 3
 *     or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
 *
 *     Find the sum of all the multiples of 3 or 5 below 1000."
 *
 * When the max is 10, the solution is 23. When the max is 100, the solution
 * is 2318.
 *
 * @author Sophie Engle
 * @author CS 212 Software Development
 * @author University of San Francisco
 */
public class ProjectEuler01c {

	public static int sumMultiples(int max) {
		int sum = 0;

		for (int i = 0; i < max; i++) {
			if (((i % 3) == 0) || ((i % 5) == 0)) {
				sum += i;
			}
		}

		return sum;
	}

	/*
	 * Here, we try to read in the maximum value from the arguments. This
	 * solution is more generalized since we can solve for any maximum value.
	 *
	 * However, we still aren't there yet. What happens if no value or an
	 * invalid value is provided? This example is not robust.
	 */
	public static void main(String[] args) {
		int max = Integer.parseInt(args[0]);
		System.out.println(sumMultiples(max));
	}
}
