import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class demonstrates how to use a {@link DirectoryStream} to create a
 * basic file listing.
 *
 * Please note this class is designed to illustrate a specific concept, and
 * is not an example of good class design outside of this context.
 *
 * @see <a href="http://docs.oracle.com/javase/tutorial/essential/io/index.html"> The Java Tutorials - Lesson: Basic I/O</a>
 * @see {@link java.nio.file.Path}
 * @see {@link java.nio.file.Paths}
 * @see {@link java.nio.file.Files}
 * @see {@link java.nio.file.DirectoryStream}
 *
 * @author Sophie Engle
 * @author CS 212 Software Development
 * @author University of San Francisco
 */
public class DirectoryStreamDemo {

	public static void main(String[] args) throws IOException {

		// Gets the current directory, and converts it to a normalized form.
		Path path = Paths.get(".").toAbsolutePath().normalize();
		System.out.println(path.getFileName() + ":");

		// If the path exists and is a directory, output the files.
		if (Files.isDirectory(path)) {
			/*
			 * The Files class has a helper method newDirectoryStream() that
			 * you can place in an enhanced-for loop to efficiently iterate
			 * through all the files and subdirectories for a path.
			 */
			for (Path file : Files.newDirectoryStream(path)) {
				/*
				 * Once we have an individual file or subdirectory, output
				 * some basic information. A "d" will be output if it is a
				 * directory, a "r" will be output if it is readable, a "w"
				 * will be output if it is writable, and an "x" will be
				 * output if it is executable. (Directories are considered
				 * executable if you can list the files in that directory.)
				 */
				System.out.print(Files.isDirectory(file)  ? "d" : "-");
				System.out.print(Files.isReadable(file)   ? "r" : "-");
				System.out.print(Files.isWritable(file)   ? "w" : "-");
				System.out.print(Files.isExecutable(file) ? "x" : "-");
				System.out.print(" " + file.getFileName());

				/*
				 * Also indicate whether the file or subdirectory is hidden.
				 * If you run this on Eclise, there should be several hidden
				 * items, like the .classpath file or the .settings directory.
				 */
				System.out.println(Files.isHidden(file) ? "\t(hidden)" : "");
			}
		}
	}
}
