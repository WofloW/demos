import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * This class demonstrates basic logging and multithreading. This is example
 * includes multiple classes, indicated in the "See Also" section.
 *
 * <p><em>
 * Note that this class is designed to illustrate a specific concept, and
 * may not be an example of proper class design outside of this context.
 * </em></p>
 *
 * @see {@link CharacterCounter}
 * @see {@link CharacterCompare}
 * @see {@link CharacterDriver}
 */
public class CharacterCounter {

    private static final Logger logger = LogManager.getLogger(CharacterCounter.class);

    /**
     * Size used for the character buffer. Set to a small size to force our
     * code to loop to demonstrate logging.
     */
    private static final int SIZE = 1000;

    /**
     * Counts the number of characters in a file. Will return -1 if unable to
     * count the characters. Note that this is a modified version of the class
     * discussed in the Debugging module.
     *
     * @param file
     * @return number of characters
     * @throws IOException
     */
    public static int countCharacters(Path file) {
        int count = 0;
        int total = 0;

        char[] buffer = new char[SIZE];

        try (
            BufferedReader reader =
                    Files.newBufferedReader(file, Charset.forName("UTF-8"));
        ) {
        		logger.debug("Counting characters in file \"{}\".", file);

            while (count >= 0) {
                total += count;
                logger.debug("Total number of characters is now {}.", total);

                count = reader.read(buffer);
                logger.debug("Read {} characters into buffer.", count);
            }

            // post-condition assert statement
            assert count < 0;
            logger.trace("Finished reading file.");

        } catch (IOException e) {
            logger.error("Unable to count characters for {}.", file);
			logger.debug("Unable to count characters.", e);
            return -1;
        }

        // post-condition assert statement
        assert total >= 0;

        logger.debug("Found {} characters total.", total);
        return total;
    }
}
