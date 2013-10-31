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
public class URLParserNullCase extends URLParserTestCase {

	@Override
	public void setUp() {

		url      = null;
		protocol = null;
		domain   = null;
		resource = null;
		query    = null;
		fragment = null;

		valid    = false;

		super.setUp();
	}
}
