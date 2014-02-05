import java.util.TreeMap;
import java.util.TreeSet;

/**
 * This class briefly demonstrates how to create a custom data structure, and
 * the dangers of returning references of this data structure. As a result,
 * this object is not encapsulated properly, and is not hiding its internal
 * data properly (even though it is set to private).
 *
 * <p><em>
 * Note that this class is designed to illustrate a specific concept, and
 * may not be an example of proper class design outside of this context.
 * </em></p>
 *
 * @see PrefixMap
 * @see PrefixDemo
 */
public class PrefixMap {

	/*
	 * Stores strings by the prefix they start with. The key is the prefix
	 * String and the value is a sorted set of strings that start with that
	 * prefix.
	 */
	private final TreeMap<String, TreeSet<String>> internal;

	/*
	 * Determines the size of the prefix used. Must be at least one letter.
	 * If a string is smaller than the prefix size, it is skipped. Since
	 * the value cannot be changed after initialization, it can be safely
	 * made public.
	 */
	public final int prefixSize;

	public PrefixMap(int prefixSize) {
		// Initialize the size of the prefix, defaulting to 1 if an invalid
		// value is provided.
		this.prefixSize = prefixSize < 1 ? 1 : prefixSize;

		/*
		 * Remember, this initializes the OUTER data structure only.
		 * You need a specific key to pair with the TreeSet before you
		 * can initialize the inner data structure.
		 */
		internal = new TreeMap<String, TreeSet<String>>();
	}

	public PrefixMap() {
		this(1);
	}

	private String convertWord(String word) {
		if (word != null) {
			// Convert word to a consistent format.
			word = word.replaceAll("\\W+", "");
			word = word.toLowerCase();
			word = word.trim();
		}

		return word;
	}

	public boolean addWord(String word) {
		// Convert word to a consistent format.
		word = convertWord(word);

		// Make sure we have a non-null word with at least as many characters
		// as the prefix size.
		if (word == null || word.length() < prefixSize) {
			return false;
		}

		String key = word.substring(0, prefixSize);

		if (!internal.containsKey(key)) {
			// Initialize inner data structure now that we have a key!
			// If you do this when the key already exists, you overwrite
			// the previous values with an empty set.
			internal.put(key, new TreeSet<String>());
		}

		// Now, we can add our word to our map. Return whether this add
		// was successful.
		return internal.get(key).add(word);
	}

	// Convenience method to add multiple words at once. Note our use of
	// addWord() to make sure we only add valid words. Yay for code reuse!
	public void addWords(String[] words) {
		for (String word : words) {
			addWord(word);
		}
	}

	// Returns whether a prefix exists in internal data structure.
	public boolean hasPrefix(String prefix) {
		return internal.containsKey(convertWord(prefix));
	}

	// Converts entire internal data structure into a string representation
	@Override
	public String toString() {
		return internal.toString();
	}

	// Unsafe method returning reference to internal data structure.
	public TreeMap<String, TreeSet<String>> getMap() {
		return internal;
	}

	// Unsafe method returning reference to internal nested data structure
	public TreeSet<String> getWords(String prefix) {
		return internal.get(convertWord(prefix));
	}
}
