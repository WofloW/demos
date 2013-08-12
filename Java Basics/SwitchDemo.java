/*
 * CS 212 Software Development (http://cs212.cs.usfca.edu/)
 * Java Basics Series - Switch Demo
 *
 * Additional Resources:
 * http://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html
 */

public class SwitchDemo {

	public static void main(String[] args) {

		// Can also use variables of type boolean, char, or String.
		int value = 1;

		/*
		 * A switch statement tries to match the value of a variable
		 * to one of the specified cases. It will then start executing
		 * statements at that case.
		 *
		 * It does not, however, stop once it hits another case.
		 * Instead, it keeps going until it hits a break statement or
		 * the end of the switch block.
		 *
		 * If the value does not match any case, nothing happens.
		 * You can, however, create a default case that will always
		 * execute if the value does not match an earlier case. For
		 * example, try values 5 and -1.
		 */
		switch (value) {
		case 1:
			System.out.println("ant");
		case 2:
			System.out.println("bee");
			break;
		case 3:
			System.out.println("cat");
		case 4:
			System.out.println("dog");
		default:
			System.out.println("fox");
		}
	}
}
