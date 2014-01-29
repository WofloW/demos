import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class demonstrates how to read and write a file using java.nio.* and
 * buffered readers and writers.
 *
 * Please note this class is designed to illustrate a specific concept, and
 * is not an example of good class design outside of this context.
 *
 * @see <a href="http://docs.oracle.com/javase/tutorial/essential/io/index.html"> The Java Tutorials - Lesson: Basic I/O</a>
 * @see {@link java.nio.file.Files}
 * @see {@link java.nio.file.Path}
 * @see {@link java.nio.file.Paths}
 *
 * @author Sophie Engle
 * @author CS 212 Software Development
 * @author University of San Francisco
 */
public class MixedCaseFileConverter {

	/**
	 * Converts a String to mixed case.
	 *
	 * @param text to be converted
	 * @return mixed-case text
	 */
	public static String toMixedCase(String text) {
		char[] characters = text.toCharArray();

		for (int i = 0; i < characters.length; i++) {
			if (Math.random() > 0.5) {
				characters[i] = Character.toLowerCase(characters[i]);
			}
			else {
				characters[i] = Character.toUpperCase(characters[i]);
			}
		}

		return String.valueOf(characters);
	}

	/**
	 * Converts the input file to mixed case, and writes the result to
	 * the output file.
	 *
	 * @param input file to convert to mixed case
	 * @param output file to save results
	 */
	public static void convertMixedCase(Path input, Path output) {

		/*
		 * You may need to change the charset/encoding depending on your
		 * system and the type of files being read. For this class, however,
		 * use UTF-8 for all reading and writing.
		 */
		Charset charset = Charset.forName("UTF-8");

		/*
		 * The try-with-resources block will automatically close both the
		 * reader and the writer.
		 *
		 * Also note the use of the helper methods in the Files class to
		 * create the buffered reader and writer.
		 */
		try (
			BufferedReader reader = Files.newBufferedReader(input, charset);
			BufferedWriter writer = Files.newBufferedWriter(output, charset);
		) {
			String line = null;

			/*
			 * It is a common pattern in Java to read a line from the file
			 * and check if it is null (hence the end of the file was reached)
			 * in the while condition. As a result, the while loop will only
			 * execute when you have a valid line from the file.
			 */
			while ((line = reader.readLine()) != null) {
				/*
				 * The buffered reader automatically removes newline
				 * characters, so we have to add them back in when writing
				 * the file back out. Use the newLine() method so that you
				 * are always outputting the correct characters for the
				 * encoding. (On Windows, for example, the end of a line is
				 * sometimes denoted as \r\n instead of just \n.)
				 */
				writer.write(toMixedCase(line));
				writer.newLine();
			}
		} catch (IOException e) {
			/*
			 * While debugging, you should do e.printStackTrace() so you know
			 * exactly what happened. However, when polishing your code for
			 * the user, replace that with a user-friendly error message.
			 */
			System.err.println("Unable to convert the input file " +
					input.getFileName() + " to mixed case.");
		}
	}

	/**
	 * Converts this source file to mixed case, assuming it exists
	 * in the "src" subdirectory.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		String name = MixedCaseFileConverter.class.getName() + ".java";
		Path file = Paths.get("src", name);

		if (Files.isReadable(file)) {
			convertMixedCase(file, Paths.get("mixedcase.txt"));
			System.out.println("Wrote converted file to mixedcase.txt.");
		}
		else {
			System.err.println("Could not find " + file.getFileName() + ".");
		}
	}
}
