import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class briefly demonstrates how to create a custom data structure, and
 * how to handle basic file i/o.
 *
 * There are two classes in this example:
 *
 *     - PrefixMap.java
 *     - PrefixMapParser.java
 *     - PrefixMapDemo.java
 *
 * Please note this class is designed to illustrate a specific concept, and
 * may not be an example of good class design outside of this context.
 *
 * @author Sophie Engle
 * @author CS 212 Software Development
 * @author University of San Francisco
 */
public class PrefixMapParser {

	/**
	 * All of the input/output in this helper class will assume files are
	 * encoded using UTF-8.
	 */
	private static final Charset UTF8 = Charset.forName("UTF-8");

	/**
	 * Opens a file, and adds words line-by-line into a {@link PrefixMap}.
	 * Will output a stack trace if the {@link PrefixMap} is in debug mode.
	 *
	 * @param map to load words into
	 * @param input path to process and load into map
	 * @return number of lines processed from input
	 */
	public static int loadMap(PrefixMap map, Path input) {
		String line = null;
		int count = 0;

		try (
			BufferedReader reader = Files.newBufferedReader(input, UTF8);
		) {
			while ((line = reader.readLine()) != null) {
				map.addWords(line);
				count++;
			}
		}
		catch (NoSuchFileException e) {
			if (PrefixMap.debug) {
				e.printStackTrace();
			}

			System.out.println("Could not find file " + input + ".");
		}
		catch (IOException e) {
			if (PrefixMap.debug) {
				e.printStackTrace();
			}

			System.out.println("Could not read file " + input + ".");
		}
		catch (Exception e) {
			if (PrefixMap.debug) {
				e.printStackTrace();
			}

			System.out.println(e.getMessage());
		}

		return count;
	}

	/**
	 * A helper method that makes sure we always output the map in a
	 * consistent format, no matter if it is to the console or the file.
	 *
	 * @param writer to use for output
	 */
	private static void printOutput(PrefixMap map, PrintWriter writer) {
		for (String prefix : map.getPrefixSet()) {
			writer.println("[" + prefix.toUpperCase() + "]");

			for (String word : map.getWordSet(prefix)) {
				writer.println(word);
			}

			writer.println();
		}

		writer.flush();
	}

	/**
	 * Prints the map to the console. Note that we use a try-with-resources
	 * block so the writer will be auto-closed, but do not have a catch block.
	 */
	public static void toConsole(PrefixMap map) {
		try (PrintWriter writer = new PrintWriter(System.out);) {
			printOutput(map, writer);
		}
	}

	/**
	 * Prints the map to a file. Will output the stack trace if the PrefixMap
	 * is in debug mode.
	 *
	 * @param path to file output
	 */
	public static void toFile(PrefixMap map, Path path) {
		try (
			PrintWriter writer = new PrintWriter(
					Files.newBufferedWriter(path, UTF8), true);
		) {
			printOutput(map, writer);
		}
		catch (IOException e) {
			if (PrefixMap.debug) {
				e.printStackTrace();
			}

			System.out.println("Unable to write prefix map to " +
					path.getFileName() + ".");
		}
	}

	/**
	 * @see {@link #toFile(Path)};
	 * @param path to file output
	 */
	public static void toFile(PrefixMap map, String path) {
		toFile(map, Paths.get(path));
	}
}
