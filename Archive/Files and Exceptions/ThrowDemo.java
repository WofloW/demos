import java.io.FileInputStream;
import java.io.FileReader;

/**
 * This class demonstrates how to re-throw exceptions. To prevent the stack
 * trace from being printed out to the console for a user, this exception
 * should be eventually caught!
 *
 * Please note this class is designed to illustrate a specific concept, and
 * is not an example of good class design outside of this context.
 *
 * @see <a href="http://docs.oracle.com/javase/tutorial/essential/exceptions/index.html"> The Java Tutorials - Lesson: Exceptions</a>
 *
 * @author Sophie Engle
 * @author CS 212 Software Development
 * @author University of San Francisco
 */
public class ThrowDemo {

	/**
	 * A simple method that purposely causes {@link FileReader} to throw an
	 * exception.
	 *
	 * @throws Exception
	 */
	public static void a() throws Exception {
		new FileReader("");
	}

	/**
	 * Calls {@link #a()}, and rethrows the exception instead of handling it.
	 * @throws Exception
	 */
	public static void b() throws Exception {
		a();
	}

	/**
	 * Calls {@link #b()}, and rethrows the exception instead of handling it.
	 * @throws Exception
	 */
	public static void c() throws Exception {
		b();
	}

	/**
	 * Calls {@link #c()}, and rethrows the exception. The stack trace for all
	 * exceptions thrown by {@link #main(String[])} will be print to the
	 * console, which is not user-friendly. However, we as developers can
	 * parse through this stack trace to find the bug.
	 *
	 * To read the stack trace, you want to find the earliest line of code
	 * within your file that the exception was generated. In this case, we
	 * do not care that the exception originated in line 138 of {@link
	 * FileInputStream}. We do care, however, that the exception was thrown
	 * on line 27 of {@link ThrowDemo}. This is where you should start
	 * debugging your code.
	 *
	 * @param args unused
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		c();
	}

}
