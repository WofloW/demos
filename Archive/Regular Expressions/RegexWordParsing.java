/**
 * Demonstrates basic word matching in regular expressions. Requires
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
public class RegexWordParsing {

	public static void main(String[] args) {

		System.out.println("Start of Word \\b\\w");
		RegexHelper.showMatches(RegexHelper.sample, "\\b\\w");

		System.out.println();

		System.out.println("End of Word \\w\\b");
		RegexHelper.showMatches(RegexHelper.sample, "\\w\\b");

		System.out.println();

		System.out.println("Words (Sans Hyphen) \\b\\w+\\b");
		RegexHelper.showMatches(RegexHelper.sample, "\\b\\w+\\b");

		System.out.println();

		System.out.println("Words (Sans Hyphen) \\w+");
		RegexHelper.showMatches(RegexHelper.sample, "\\w+");

		System.out.println();

		System.out.println("Words (With Hyphen) \\S+\\b");
		RegexHelper.showMatches(RegexHelper.sample, "\\S+\\b");

		System.out.println();

		System.out.println("Words (With Symbol) \\S+");
		RegexHelper.showMatches(RegexHelper.sample, "\\S+");

		System.out.println();

		System.out.println("Words that start with S (?i)s\\S+\\b");
		RegexHelper.showMatches(RegexHelper.sample, "(?i)s\\S+\\b");

		System.out.println();

		System.out.println("Words that contain an e \\b\\S*e\\S*\\b");
		RegexHelper.showMatches(RegexHelper.sample, "\\b\\S*e\\S*\\b");
	}
}
