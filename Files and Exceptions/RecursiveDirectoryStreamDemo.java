import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class demonstrates how to use a {@link DirectoryStream} to create a
 * recursive file listing. You can also do this using a {@link FileVisitor},
 * but we won't discuss this until we cover inheritance.Ê
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
public class RecursiveDirectoryStreamDemo {

	/**
	 * Outputs the name of the file or subdirectory, with proper indentation
	 * to help indicate the hierarchy. If a subdirectory is encountered, will
	 * recursively list all the files in that subdirectory.
	 *
	 * The recursive version of this method is private. Users of this class
	 * will have to use the public version (see below).
	 *
	 * @param prefix the padding or prefix to put infront of the file or subdirectory name
	 * @param path to retrieve the listing, assumes a directory and not a file is passed
	 * @throws IOException
	 */
	private static void traverse(String prefix, Path path) throws IOException {
		/*
		 * The try-with-resources block makes sure we close the directory
		 * stream when done, to make sure there aren't any issues later
		 * when accessing this directory.
		 *
		 * Note, however, we are still not catching any exceptions. This
		 * type of try block does not have to be accompanied with a catch
		 * block. (You should, however, do something about the exception.)
		 */
		try (DirectoryStream<Path> listing = Files.newDirectoryStream(path)) {
			// Efficiently iterate through the files and subdirectories.
			for (Path file : listing) {
				// Print the name with the proper padding/prefix.
				System.out.println(prefix + file.getFileName());

				// If it is a subdirectory, recursively traverse.
				if (Files.isDirectory(file)) {
					// Add a little bit of padding so files in subdirectory
					// are indented under that directory.
					traverse("  " + prefix, file);
				}
			}
		}
	}

	/**
	 * Safely starts the recursive traversal with the proper padding. Users
	 * of this class can access this method, so some validation is required.
	 *
	 * @param directory to traverse
	 * @throws IOException
	 */
	public static void traverse(Path directory) throws IOException {
		if (Files.isDirectory(directory)) {
			traverse("- ", directory);
		}
		else {
			System.out.println(directory.getFileName());
		}
	}

	/**
	 * Recursively traverses the current directory and prints the file listing.
	 * @param args unused
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Path path = Paths.get(".").toAbsolutePath().normalize();
		System.out.println(path.getFileName() + ":");
		traverse(path);
	}

}
