import java.util.Random;

/**
 * This is a simple class to demonstrate some basics in Java, including
 * getting command-line parameters using an enhanced for-loop, generating
 * random numbers, and using a switch statement.
 *
 * For more information on the Magic Eight Ball toy, see:
 *
 *     http://en.wikipedia.org/wiki/Magic_8-Ball
 *
 * @author Sophie Engle
 * @author CS 212 Software Development
 * @author University of San Francisco
 */

public class MagicEight2 {

	public static void main(String[] args) {

		System.out.print("Enter question: ");

		// This is how you print out all of the command-line arguments.
		for (String arg : args) {
			System.out.print(arg + " ");
		}

		System.out.println();

		// This is how you generate random numbers.
		Random random = new Random(System.currentTimeMillis());

		// This will generate a random integer between 0 and 6.
		int value = random.nextInt(6);

		switch (value) {
		case 0:
			System.out.println("You may rely on it.");
			break;
		case 1:
			System.out.println("Signs point to yes.");
			break;
		case 2:
			System.out.println("Reply hazy, try again.");
			break;
		case 3:
			System.out.println("Concentrate and ask again.");
			break;
		case 4:
			System.out.println("My sources say no.");
			break;
		default:
			System.out.println("Outlook is poor.");
		}

	}
}
