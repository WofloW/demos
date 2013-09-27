import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class demonstrates basic logging and multithreading. This is example
 * includes multiple classes, indicated in the "See Also" section.
 *
 * Please note this class is designed to illustrate a specific concept, and
 * is not an example of good class design outside of this context.
 *
 * @see CharacterCounter
 * @see CharacterCompare
 * @see CharacterDriver
 *
 * @author Sophie Engle
 * @author CS 212 Software Development
 * @author University of San Francisco
 */
public class CharacterCompare {

	private static final Logger logger = LogManager.getLogger(CharacterCompare.class);

	/**
	 * Compare two files, returning the difference in the number of characters
	 * using the {@link CharacterCounter#countCharacters(Path)} method.
	 *
	 * This method does not perform any exception handling for simplicity! If
	 * there are any issues reading the files, the result will be meaningless.
	 *
	 * @param file1
	 * @param file2
	 * @return difference in characters
	 */
	public static int compareSequentially(Path file1, Path file2) {
		logger.debug("Comparing {} and {} sequentially.", file1, file2);
		int count1 = CharacterCounter.countCharacters(file1);
		int count2 = CharacterCounter.countCharacters(file2);

		logger.debug("{} has {} characters.", file1.getFileName(), count1);
		logger.debug("{} has {} characters.", file2.getFileName(), count2);

		return count1 - count2;
	}

	/**
	 * A thread subclass used to concurrently compare the number of characters
	 * in two files. Demonstrates one of two ways to create a {@link Thread}
	 * object.
	 */
	private static class CountThread extends Thread {
		private Path file;
		private int count;

		public CountThread(Path file) {
			this.file = file;
			this.count = 0;
		}

		/**
		 * This method must match exactly, so we are unable to add parameters,
		 * change the return type, or throw any exceptions.
		 */
		@Override
		public void run() {
			count = CharacterCounter.countCharacters(file);
			logger.debug("{} has {} characters.", file, count);
		}
	}

	/**
	 * Compare two files, returning the difference in the number of characters
	 * using the {@link CharacterCounter#countCharacters(Path)} method.
	 *
	 * This method does not perform any exception handling for simplicity! If
	 * there are any issues reading the files, the result will be meaningless.
	 *
	 * @param file1
	 * @param file2
	 * @return difference in characters
	 */
	public static int compareConcurrently(Path file1, Path file2) {
		logger.debug("Comparing {} and {} concurrently.", file1, file2);
		CountThread counter1 = new CountThread(file1);
		CountThread counter2 = new CountThread(file2);

		counter1.start();
		counter2.start();

		try {
			logger.debug("Waiting for threads to complete.");
			counter1.join();
			counter2.join();
			logger.debug("Threads finished.");
		}
		catch (InterruptedException e) {
			logger.debug("Unable to compare concurrently.", e);
		}

		return counter1.count - counter2.count;
	}
}
