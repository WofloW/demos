import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses an email into its local, domain, and top-level domain components.
 * Does not do full email validation.
 *
 * Demonstrates regular expressions and unit testing.
 *
 * @author Sophie Engle
 * @author CS 212 Software Development
 * @author University of San Francisco
 *
 * @see EmailParser
 * @see EmailParserTest
 */
public class EmailParser {
	private String email;	// sjengle@cs.usfca.edu
	private String local;	// sjengle
	private String domain;	// cs.usfca.edu
	private String tld;		// edu

	private boolean valid;	// Indicates if email was parsable

	public EmailParser(String email) {
		if (email == null) {
			return;
		}

		/*
		 * Try the following regular expressions, and
		 * see which ones pass and fail. Make sure you
		 * understand both the regex, and the unit tests.
		 */

		String regex = null;

		// regex = "(.+)@(.+\\.(.+))";
		// regex = "([^@]*)@([^@]*\\.([^@]*))";
		regex = "^([^@]+)@([^@]+\\.([^.@]+))$";

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(email);

		/*
		 * Use matches() instead of find() to match against the entire string.
		 */

		if (!m.matches()) {
			valid = false;
			return;
		}

		assert m.groupCount() == 4;

		valid = true;

		this.email  = m.group(0);
		this.local  = m.group(1);
		this.domain = m.group(2);
		this.tld    = m.group(3);
	}

	/*
	 * No other set methods are necessary, as we only need to set the email
	 * and that is taken care of by the constructor.
	 */

	public boolean isValid() {
		return valid;
	}

	public String getLocal() {
		return local;
	}

	public String getDomain() {
		return domain;
	}

	public String getTLD() {
		return tld;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return getEmail();
	}

	public String debug() {
		return String.format("Email: %s, Local: %s, Domain: %s, TLD: %s",
				getEmail(), getLocal(), getDomain(), getTLD());
	}
}
