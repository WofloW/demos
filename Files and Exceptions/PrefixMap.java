import java.util.Collections;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * This class briefly demonstrates how to create a custom data structure, and
 * how to handle basic file i/o.
 *
 * There are two classes in this example:
 *
 *     - PrefixMap.java
 *     - PrefixMapHelper.java
 *     - PrefixMapDemo.java
 *
 * Please note this class is designed to illustrate a specific concept, and
 * may not be an example of good class design outside of this context.
 *
 * @author Sophie Engle
 * @author CS 212 Software Development
 * @author University of San Francisco
 */
public class PrefixMap {

	private TreeMap<String, TreeSet<String>> internal;
	public final int prefixSize;
	public static boolean debug = false;

	public PrefixMap(int prefixSize) {
		this.prefixSize = (prefixSize < 1) ? 1 : prefixSize;
		this.internal = new TreeMap<String, TreeSet<String>>();
	}

	public PrefixMap() {
		this(1);
	}

	/**
	 * Converts a word to a consistent format for internal storage.
	 *
	 * @param word
	 * @return converted word
	 */
	private String convertWord(String word) {
		if (word != null) {
			word = word.replaceAll("\\W+", "");
			word = word.toLowerCase();
			word = word.trim();
		}

		return word;
	}

	/**
	 * Adds a word to the prefix map.
	 *
	 * @param word to be added
	 * @return {@code true} if the word was successfully added
	 */
	public boolean addWord(String word) {
		word = convertWord(word);

		if ((word == null) || (word.length() < prefixSize)) {
			return false;
		}

		String key = word.substring(0, prefixSize);

		if (!internal.containsKey(key)) {
			internal.put(key, new TreeSet<String>());
		}

		return internal.get(key).add(word);
	}

	/**
	 * Adds several words at once to the prefix map.
	 *
	 * @param words that have already been parsed
	 */
	public void addWords(String[] words) {
		for (String word : words) {
			addWord(word);
		}
	}

	/**
	 * Adds unparsed text to the prefix map, first parsing it into words
	 * by whitespace characters.
	 *
	 * @param words
	 */
	public void addWords(String words) {
		addWords(words.split("\\s"));
	}

	/**
	 * Tests whether a particular prefix exists in the prefix map.
	 *
	 * @param prefix to search for
	 * @return {@code true} if the prefix exists
	 */
	public boolean hasPrefix(String prefix) {
		return internal.containsKey(convertWord(prefix));
	}

	/**
	 * Gets the number of prefixes currently stored in the map.
	 *
	 * @return number of prefixes
	 */
	public int numPrefix() {
		return internal.size();
	}

	@Override
	public String toString() {
		return internal.toString();
	}

	/**
	 * This method returns a reference to a private mutable object, and hence
	 * should not be public. However, it might be useful internally.
	 *
	 * @param prefix to get all words
	 * @return reference to set of words associated with the prefix
	 */
	private TreeSet<String> getWords(String prefix) {
		return internal.get(convertWord(prefix));
	}

	/**
	 * Safely returns the set of prefixes.
	 *
	 * @param prefix to get all words
	 * @return an unmodifiable set of prefixes
	 */
	public Set<String> getPrefixSet() {
		// Does not make a copy, just "intercepts" any modifications.
		return Collections.unmodifiableSet(internal.keySet());
	}

	/**
	 * Safely returns the prefixes as an array. This causes a copy to occur,
	 * but afterwards the array may be modified without affecting the original
	 * set. This method should be used sparingly.
	 *
	 * @return set of prefixes copied into an array
	 */
	public String[] getPrefixArray() {
		String[] holder = new String[] {};
		return internal.keySet().toArray(holder);
	}

	/**
	 * Safely returns the set of words associated with a prefix.
	 *
	 * @param prefix to get all words
	 * @return an unmodifiable set of words associated with the prefix
	 */
	public Set<String> getWordSet(String prefix) {
		TreeSet<String> words = getWords(prefix);

		if (words != null) {
			// Does not make a copy, just "intercepts" any modifications.
			return Collections.unmodifiableSortedSet(words);
		}

		return null;
	}

	/**
	 * Returns the words associated with a prefix as an array. This causes a
	 * copy to occur, but afterwards the array may be modified without
	 * affecting the original set. This method should be used sparingly.
	 *
	 * @param prefix to get all words
	 * @return set of words associated with the prefix copied into an array
	 */
	public String[] getWordArray(String prefix) {
		TreeSet<String> words = getWords(prefix);
		String[] holder = new String[] {};

		if (words != null) {
			// Makes a copy, and returns that copy. Expensive!
			return words.toArray(holder);
		}

		return holder;
	}
}
