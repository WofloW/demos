
/**
 * Demonstrates basic boundary matching in regular expressions. Shows how
 * the MULTILINE (?m) and DOTALL (?s) flags changes the results. Requires
 * the {@link RegexHelper} class.
 *
 * @author Sophie Engle
 * @author CS 212 Software Development
 * @author University of San Francisco
 *
 * @see RegexHelper
 * @see RegexClasses
 * @see RegexQuantifiers
 * @see RegexBoundaries
 * @see RegexWordParsing
 * @see RegexQuiz
 */
public class RegexBoundaries {

	public static void main(String[] args) {

		// Example string for testing regular expressions.
		String text = "Knock knock!\nWho's there?";
		System.out.println(text);

		System.out.println();

		System.out.println("Input Boundary \\A and \\z");
		RegexHelper.printMatches(text, "\\A.*\\z");		// .* will not match \n
		RegexHelper.printMatches(text, "(?s)\\A.*\\z");	// .* will match \n

		System.out.println();

		System.out.println("Line Boundary ^ and $");
		RegexHelper.printMatches(text, "^.*$");			// .* will not match \n
		RegexHelper.printMatches(text, "(?m)^.*$");		// ^$ will look at individual lines
		RegexHelper.printMatches(text, "(?s)^.*$");		// .* will match \n
		RegexHelper.printMatches(text, "(?ms)^.*$");		// greedy, matches everything
		RegexHelper.printMatches(text, "(?ms)^.*?$");	// reluctant, matches each line
	}
}
