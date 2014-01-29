/**
 * Part of the {@link URLParser} example. Demonstrates regular expressions,
 * test-driven development, and JUnit test suites.
 *
 * @author Sophie Engle
 * @author CS 212 Software Development
 * @author University of San Francisco
 *
 * @see URLParser
 * @see URLParserTestCase
 * @see URLParserTestSuite
 */
public class URLParserSimpleCase extends URLParserTestCase {

	@Override
	public void setUp() {
		url      = "http://www.google.com";
		protocol = "http";
		domain   = "www.google.com";
		resource = "/";
		query    = null;
		fragment = null;

		valid    = true;

		super.setUp();
	}
}
