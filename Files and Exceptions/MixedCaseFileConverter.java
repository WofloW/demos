import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



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

		// You many need to change this depending on your system
		// and the type of files being read.
		Charset charset = Charset.defaultCharset();

		// The try-with-resources block will auto-close the files.
		try (
			BufferedReader reader = Files.newBufferedReader(input, charset);
			BufferedWriter writer = Files.newBufferedWriter(output, charset);
		) {
			String line = null;

			// Read the line and check for EOF within while condition.
			while ((line = reader.readLine()) != null) {
				// Write the converted line.
				writer.write(toMixedCase(line));

				// Re-add the newline character, since it is stripped by the
				// readLine() method of the BufferedReader.
				writer.newLine();
			}
		} catch (IOException e) {
			System.err.println("Unable to convert the input file " +
					input.getFileName() + " to mixed case.");
		}
	}

	public static void convertMixedCase(String input, String output) {
		convertMixedCase(Paths.get(input), Paths.get(output));
	}

	public static void convertMixedCase(File input, File output) {
		convertMixedCase(input.toPath(), output.toPath());
	}

	/**
	 * Converts this source file to mixed case, assuming it exists
	 * in the "src" directory.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		String name = MixedCaseFileConverter.class.getName() + ".java";
		convertMixedCase("src/" + name, "mixedcase.txt");
	}
}
