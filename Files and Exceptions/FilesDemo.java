import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class demonstrates how to use the {@link Files} class to get basic
 * file information.
 *
 * Please note this class is designed to illustrate a specific concept, and
 * is not an example of good class design outside of this context.
 *
 * @see <a href="http://docs.oracle.com/javase/tutorial/essential/io/index.html"> The Java Tutorials - Lesson: Basic I/O</a>
 * @see {@link java.nio.file.Files}
 *
 * @author Sophie Engle
 * @author CS 212 Software Development
 * @author University of San Francisco
 */
public class FilesDemo {

	/** Format string used for easy-to-read console output. */
	private static final String format = "%22s: %s%n";

	/**
	 * Demonstrates the {@link Files} class.
	 * @throws Exception if the source code for {@link FilesDemo} is not
	 * accessible in the src subdirectory.
	 */
	public static void main() throws Exception {

		// Assumes source code is located in the src subdirectory.
		Path path = Paths.get("src", "FilesDemo.java").toAbsolutePath();
		System.out.printf(format, "getName()", path.getFileName());
		System.out.println();

		// Get basic information about the file.
		System.out.printf(format, "getLastModifiedTime()", Files.getLastModifiedTime(path));
		System.out.printf(format, "size()", Files.size(path) + " bytes");
		System.out.printf(format, "getOwner()", 	Files.getOwner(path));
		System.out.println();

		// Check whether the file is a directory or hidden.
		System.out.printf(format, "exists()", Files.exists(path));
		System.out.printf(format, "isDirectory()", Files.isDirectory(path));
		System.out.printf(format, "isHidden()", Files.isHidden(path));
		System.out.println();

		// Check the read/write/execute permissions of the file.
		System.out.printf(format, "isExecutable()", Files.isExecutable(path));
		System.out.printf(format, "isReadable()", Files.isReadable(path));
		System.out.printf(format, "isWritable()", Files.isWritable(path));
	}
}
