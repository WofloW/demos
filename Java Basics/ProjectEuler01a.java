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
public class ProjectEuler01a {

	/*
	 * This is a basic example. However, it is not generalized or reusable.
	 * You must change the source code to change the maximum value, and there
	 * is no way to reuse this code elsewhere without using copy/paste.
	 */
	public static void main(String[] args) {
		int max = 10;
		int sum = 0;

		for (int i = 0; i < max; i++) {
			if (((i % 3) == 0) || ((i % 5) == 0)) {
				sum += i;
			}
		}

		System.out.println(sum);
	}
}
