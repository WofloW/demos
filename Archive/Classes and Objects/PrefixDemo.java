
/**
 * This class briefly demonstrates how to create a custom data structure, and
 * the dangers of returning references of this data structure. As a result,
 * this object is not encapsulated properly, and is not hiding its internal
 * data properly (even though it is set to private).
 *
 * There are two classes in this example:
 *
 *     - PrefixMap.java
 *     - PrefixDemo.java
 *
 * Please note this class is designed to illustrate a specific concept, and
 * may not be an example of good class design outside of this context.
 *
 * @author Sophie Engle
 * @author CS 212 Software Development
 * @author University of San Francisco
 */
public class PrefixDemo {

	public static void main(String[] args) {

		String[] words = {"ant", "antelope", "ape", "b-a-t", "badger", "CAT",
				"cat@fish", "dog  ", "  dragonfly"};

		PrefixMap map = new PrefixMap(2);

		map.addWords(words);
		System.out.println(map);

		/*
		 * Even though we cannot access the "internal" data structure
		 * directly, we can access it through the returned reference and
		 * run amok with the data.
		 */
		map.getMap().remove("do");
		System.out.println(map);

		/*
		 * Note that this works with the inner data structure as well!
		 */
		map.getWords("ba").clear();
		System.out.println(map);

		/*
		 * At this point, we have completely broken the integrity of our
		 * data structure. We can no longer guarantee that the map stores
		 * prefixes and strings that start with that prefix.
		 */
		map.getWords("ba").add("zebra");
		System.out.println(map);
	}
}
