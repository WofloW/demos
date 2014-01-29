import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * An example showing how to use the DatabaseConnector and JDBC to connect to
 * a database and retrieve information safely without directly using user
 * input to avoid an XSS or SQL injection attack.
 *
 * @see DatabaseConenctor
 * @see ContactServer
 * @see ContactSimpleServlet
 * @see ContactComplexServlet
 */
@SuppressWarnings("serial")
public class ContactSimpleServlet extends HttpServlet {

	/** SQL SELECT statement without the ORDER BY clause. */
	protected static final String SELECT = "SELECT " +
			"CONCAT(contact_users.first, ' ', contact_users.last) AS 'Name', " +
			"contact_users.position AS 'Position', " +
			"CONCAT('(', contact_phones.area, ') ', contact_phones.phone) AS 'Phone', " +
			"GROUP_CONCAT(contact_emails.email SEPARATOR ',') AS 'Email', " +
			"IFNULL(contact_websites.website, '') AS 'Website' " +
			"FROM contact_users " +
			"NATURAL LEFT OUTER JOIN contact_phones " +
			"NATURAL LEFT OUTER JOIN contact_emails " +
			"NATURAL LEFT OUTER JOIN contact_websites " +
			"GROUP BY contact_users.userid ";

	/** Pre-existing database connector. */
	protected final DatabaseConnector connector;

	/** Initiailizes database connector to use for all servlet requests. */
	public ContactSimpleServlet(DatabaseConnector connector) {
		this.connector = connector;
	}

	/**
	 * Responds to HTTP GET requests with a simple web page containing a
	 * static table of contacts.
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		prepareResponse(response);
		outputHeaders(request, response);
		outputContacts(request, response);
		finishResponse(response);
	}

	/**
	 * Prepares the servlet response by writing header HTML to the web page.
	 *
	 * @param response
	 * @throws IOException
	 */
	protected static void prepareResponse(HttpServletResponse response)
			throws IOException {

		PrintWriter out = response.getWriter();
		out.printf("<!DOCTYPE html>%n");
		out.printf("<html lang=\"en\">%n");
		out.printf("<head>%n");
		out.printf("\t<meta charset=\"utf-8\">%n");
		out.printf("\t<title>Contact Listing</title>%n");
		out.printf("</head>%n%n");
		out.printf("<body>%n");
		out.printf("<table cellspacing=\"0\" cellpadding=\"2\" border=\"1\">%n");
	}

	/**
	 * Finishes the servlet response by writing footer HTML to the web page
	 * and setting the status code to 200 OK.
	 *
	 * @param response
	 * @throws IOException
	 */
	protected static void finishResponse(HttpServletResponse response)
			throws IOException {

		PrintWriter out = response.getWriter();
		out.printf("</table>%n");
		out.printf("%n</body>%n</html>%n");
		out.flush();

		response.setStatus(HttpServletResponse.SC_OK);
		response.flushBuffer();
	}

	/**
	 * Outputs the table headers, containing the column names.
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	protected void outputHeaders(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String cellFormat = "\t<td><b>%s</b></td>%n";
		PrintWriter out = response.getWriter();

		out.printf("<tr style=\"background-color: #EEEEEE;\">%n");
		out.printf(cellFormat, "Name");
		out.printf(cellFormat, "Position");
		out.printf(cellFormat, "Phone");
		out.printf(cellFormat, "Email");
		out.printf(cellFormat, "Website");
		out.printf("</tr>%n");
	}

	/**
	 * Outputs the contact information, without any links.
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	protected void outputContacts(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		PrintWriter out = response.getWriter();
		String cellFormat = "\t<td>%s</td>%n";

		try (
			Connection db = connector.getConnection();
			Statement statement = db.createStatement();
			ResultSet results = statement.executeQuery(SELECT + ";");
		) {
			// The text used in results.getString(String) must match the
			// column names in the SELECT statement.
			while (results.next()) {
				out.printf("<tr>%n");
				out.printf(cellFormat, results.getString("Name"));
				out.printf(cellFormat, results.getString("Position"));
				out.printf(cellFormat, results.getString("Phone"));
				out.printf(cellFormat, results.getString("Email"));
				out.printf(cellFormat, results.getString("Website"));
				out.printf("</tr>%n");
			}
		}
		catch (SQLException e) {
			out.printf("\t<td colspan=\"5\">%s</td>%n", e.getMessage());
		}
	}
}
