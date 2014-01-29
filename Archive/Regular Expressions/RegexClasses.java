/**
 * Demonstrates basic character classes in regular expressions. Requires
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
public class RegexClasses {

	public static void main(String[] args) {

		System.out.println("Lowercase s");
		RegexHelper.showMatches(RegexHelper.sample, "s");

		System.out.println();

		System.out.println("Lowercase or Uppercase [sS]");
		RegexHelper.showMatches(RegexHelper.sample, "[sS]");

		System.out.println();

		System.out.println("Lowercase or Uppercase (?i)s");
		RegexHelper.showMatches(RegexHelper.sample, "(?i)s");

		System.out.println();

		/*
		 * The above example shows how to use the (?i) flag in a regex.
		 */

		System.out.println("Lowercase Letters [a-z]");
		RegexHelper.showMatches(RegexHelper.sample, "[a-z]");

		System.out.println();

		System.out.println("Uppercase  Letters \\p{Upper}");
		RegexHelper.showMatches(RegexHelper.sample, "\\p{Upper}");

		System.out.println();

		/*
		 * As demonstrated above, there are several ways to specify
		 * equivalent character classes.
		 */

		System.out.println("Digit Characters \\d");
		RegexHelper.showMatches(RegexHelper.sample, "\\d");

		System.out.println();

		/*
		 * Notice in digit output above that each digit 7 and 6 are
		 * individual matches.
		 */

		System.out.println("Word Characters \\w");
		RegexHelper.showMatches(RegexHelper.sample, "\\w");

		System.out.println();

		System.out.println("Non-Whitespace Characters \\S");
		RegexHelper.showMatches(RegexHelper.sample, "\\S");

		System.out.println();

		/*
		 * Notice difference between word and non-whitespace characters
		 * above is whether the symbols match.
		 */

		System.out.println("Whitespaces \\s");
		RegexHelper.showMatches(RegexHelper.sample, "\\s");

		System.out.println();

		System.out.println("Non-Word Characters \\W");
		RegexHelper.showMatches(RegexHelper.sample, "\\W");

		System.out.println();

		/*
		 * Notice difference between whitespace and non-word characters
		 * above is whether the symbols match.
		 */

		System.out.println("Any Character .");
		RegexHelper.showMatches(RegexHelper.sample, ".");
	}
}
