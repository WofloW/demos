import java.util.Random;
import java.util.Scanner;

/**
 * This is a simple class to demonstrate some basics in Java, including
 * defining an array, prompting for user input using try-with-resources,
 * and generating a random number within the bounds of an array.
 *
 * For more information on the Magic Eight Ball toy, see:
 *
 *     http://en.wikipedia.org/wiki/Magic_8-Ball
 *
 * @author Sophie Engle
 * @author CS 212 Software Development
 * @author University of San Francisco
 */

public class MagicEight1 {

	public static void main(String[] args) {

		// This is how you prompt the user for text using the new
		// try-with-resources block in Java 7.
		try (Scanner scanner = new Scanner(System.in);) {
			System.out.print("Enter question: ");
			scanner.nextLine();
		}

		// This is how you define an array with initial values.
		String[] messages = new String[] {
				"You may rely on it.",
				"Signs point to yes.",
				"Reply hazy, try again.",
				"Concentrate and ask again.",
				"My sources say no.",
				"Outlook is poor."
		};

		// This is how you generate random numbers.
		Random random = new Random(System.currentTimeMillis());

		// This will generate a random integer within the bounds of the array.
		int value = random.nextInt(messages.length);

		// This is how you access an array by offset or index, and
		// print the value to the console.
		System.out.println(messages[value]);
	}
}
