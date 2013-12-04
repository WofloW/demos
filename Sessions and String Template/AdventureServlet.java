import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STRawGroupDir;

@SuppressWarnings("serial")
public class AdventureServlet extends HttpServlet {

	private static Logger log = Log.getRootLogger();

	private static final STGroup templates =
			new STRawGroupDir("st", '$', '$');

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		ST st = templates.getInstanceOf("main");

		PrintWriter writer = res.getWriter();
		HttpSession session = req.getSession(true);

		// tracks current game state
		AdventureRoom room = null;
		Direction direction = null;

		// enables/disables direction buttons
		String west = null;
		String east = null;
		String north = null;
		String south = null;

		// try to get current game state
		try {
			room = (AdventureRoom) session.getAttribute("room");
			direction = Direction.valueOf(req.getParameter("direction"));
		}
		catch(Exception ignored) {
			// ignored
		}

		// make sure values are valid
		if((room == null) || (direction == null)) {
			log.info("Starting new game for session " + session.getId());
			room = AdventureRoom.START_ROOM;
			direction = Direction.EAST;
		}
		else {
			room = room.moveRoom(direction);
		}

		// save the updated room value in the session
		session.setAttribute("room", room);

		if(room.done()) {
			// game won or lost
			log.info("Game finished for session " + session.getId());
			session.setAttribute("room", AdventureRoom.START_ROOM);
			session.invalidate();
			west  = "disabled";
			east  = "disabled";
			north = "disabled";
			south = "disabled";
		}
		else {
			// calculate which buttons to disable
			west  = room.canMove(Direction.WEST)  ? "" : "disabled";
			east  = room.canMove(Direction.EAST)  ? "" : "disabled";
			north = room.canMove(Direction.NORTH) ? "" : "disabled";
			south = room.canMove(Direction.SOUTH) ? "" : "disabled";
		}

		// setup template
		st.add("message", room.toString());
		st.add("west",  west);
		st.add("east",  east);
		st.add("north", north);
		st.add("south", south);

		// allows session tracking even if cookies are disabled
		st.add("action", res.encodeURL("/"));

		// add debug information
		st.add("session", session.getId());
		st.add("room", room.ordinal());
		st.add("direction", direction);

		// generate html from template
		writer.write(st.render());

		// finish up response
		res.setContentType("text/html");
		res.setStatus(HttpServletResponse.SC_OK);
		res.flushBuffer();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doGet(req, res);
	}
}
