
/**
 * Demonstrates differences between quantifiers. Requires the
 * {@link RegexHelper} class.
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
public class RegexQuantifiers {

	public static void main(String[] args) {

		String text = "aardvark";
		String regex;

		/*
		 * Basic greedy quantifiers.
		 */

		regex = "a";
		System.out.println("\nRegex: " + regex);
		RegexHelper.showMatches(text, regex);

		regex = "a*";
		System.out.println("\nRegex: " + regex);
		RegexHelper.showMatches(text, regex);

		regex = "a+";
		System.out.println("\nRegex: " + regex);
		RegexHelper.showMatches(text, regex);

		regex = "a{2}";
		System.out.println("\nRegex: " + regex);
		RegexHelper.showMatches(text, regex);

		/*
		 * Demonstrate reluctant.
		 */

		regex = "a.+r";
		System.out.println("\nRegex: " + regex);
		RegexHelper.showMatches(text, regex);

		regex = "a.+?r";
		System.out.println("\nRegex: " + regex);
		RegexHelper.showMatches(text, regex);

		/*
		 * Demonstrate possessive.
		 */

		regex = "[^k]+k";
		System.out.println("\nRegex: " + regex);
		RegexHelper.showMatches(text, regex);

		regex = "[^k]++k";
		System.out.println("\nRegex: " + regex);
		RegexHelper.showMatches(text, regex);

		regex = "[^k]+d";
		System.out.println("\nRegex: " + regex);
		RegexHelper.showMatches(text, regex);

		regex = "[^k]++d";
		System.out.println("\nRegex: " + regex);
		RegexHelper.showMatches(text, regex);

		// Possessive is trying to match entire string, no backtracking
		// is done if entire string does not match.
	}
}
