import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

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
public class ContactComplexServlet extends ContactSimpleServlet {

	/**
	 * We use this map to validate information that may originate from the
	 * user. This maps user-friendly column names to the actual column names
	 * in our SQL database.
	 */
	private final Map<String, String> tableInfo;

	/**
	 * Sets the database connector to use, and populates the table of valid
	 * column names.
	 *
	 * @param connector
	 */
	public ContactComplexServlet(DatabaseConnector connector) {
		super(connector);

		tableInfo = new LinkedHashMap<String, String>();
		tableInfo.put("Name", "contact_users.last");
		tableInfo.put("Position", "contact_users.position");
		tableInfo.put("Phone", "contact_phones.phone");
		tableInfo.put("Email", "contact_emails.email");
		tableInfo.put("Website", "contact_websites.website");
	}

	/**
	 * A helper method to get the column name to sort by. If the column name
	 * is missing or invalid (i.e. a script), we will default to a safe value.
	 *
	 * @param request
	 * @return safe column name
	 */
	private String getSortName(HttpServletRequest request) {
		String name = request.getParameter("column");

		if ((name == null) || !tableInfo.containsKey(name)) {
			name = "Name";
		}

		return name;
	}

	/**
	 * A helper method to get the sort order (ascending or descending). If
	 * the sort order is missing or invalid (i.e. a script), we will default
	 * to a safe value.
	 *
	 * @param request
	 * @return safe sort order
	 */
	private static boolean getSortOrder(HttpServletRequest request) {
		String asc = request.getParameter("asc");

		if (asc != null) {
			return Boolean.parseBoolean(request.getParameter("asc"));
		}
		else {
			return true;
		}
	}

	/**
	 * Overrides {@link ContactSimpleServlet#outputHeaders(HttpServletRequest,
	 * HttpServletResponse)} to output column names that can be clicked to
	 * change the sort order. Must be careful that the GET parameters are
	 * validated, and not used directly on the web page (XSS Attack) or in the
	 * SELECT statement (SQL Injection).
	 */
	@Override
	protected void outputHeaders(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		// table format, includes link to change sort column, sort order, and colum name
		String cellFormat = "\t<td><a href=\"/contact?column=%s&asc=%b\"><b>%s</b></a></td>%n";
		PrintWriter out = response.getWriter();

		// get parameters safely
		String sort = getSortName(request);
		boolean asc = getSortOrder(request);

		out.printf("<tr style=\"background-color: #EEEEEE;\">%n");

		for (String column : tableInfo.keySet()) {
			// if we sorted by this column, reverse the sort order option
			if (column.equalsIgnoreCase(sort)) {
				out.printf(cellFormat, column, !asc, column);
			}
			else {
				out.printf(cellFormat, column, true, column);
			}
		}

		out.printf("</tr>%n");
	}

	/**
	 * Overrides {@link ContactSimpleServlet#outputContacts(HttpServletRequest,
	 * HttpServletResponse)} to get contacts in the sort order specified. Must
	 * be careful that the GET parameters are validated, and not used directly
	 * on the web page (XSS Attack) or in the SELECT statement (SQL Injection).
	 */
	@Override
	protected void outputContacts(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		PrintWriter out = response.getWriter();

		// need different format strings to handle websites and emails
		String cellFormat = "\t<td>%s</td>%n";
		String linkFormat = "\t<td><a href=\"%s\">%s</a></td>%n";
		String mailFormat = "<a href=\"mailto:%s\">%s</a>";

		// get parameters safely
		String sort = getSortName(request);
		boolean asc = getSortOrder(request);

		// figure out ORDER BY clause to add to SELECT statement
		String orderby = "ORDER BY " + tableInfo.get(sort);
		orderby += (asc) ? " ASC;" : " DESC;";

		try (
			Connection db = connector.getConnection();
			Statement statement = db.createStatement();
			ResultSet results = statement.executeQuery(SELECT + orderby);
		) {
			while (results.next()) {
				out.printf("<tr>%n");
				out.printf(cellFormat, results.getString("Name"));
				out.printf(cellFormat, results.getString("Position"));
				out.printf(cellFormat, results.getString("Phone"));

				// handle multiple emails by splitting into individual
				// email addresses, so they can be surrounded by links
				String[] emails = results.getString("Email").split(",");
				out.printf("\t<td>" + mailFormat, emails[0], emails[0]);

				for (int i = 1; i < emails.length; i++) {
					out.printf(", " + mailFormat, emails[i], emails[i]);
				}

				out.printf("</td>%n");

				// handle website
				String website = results.getString("website");

				if ((website == null) || website.trim().isEmpty()) {
					out.printf(cellFormat, "&nbsp;");
				}
				else {
					out.printf(linkFormat, website, website);
				}

				out.printf("</tr>%n");
			}
		}
		catch (SQLException e) {
			out.printf("\t<td colspan=\"5\">%s</td>%n", e.getMessage());
		}
	}
}
