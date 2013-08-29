import java.util.ArrayList;
import java.util.Arrays;

/**
 * Demonstrates how parameter passing works in Java, including the difference
 * in behavior for immutable versus mutable objects.
 *
 * In particular, mutability is tied to how memory is handled. If new memory
 * is created when you change a value, then the original object is immutable.
 * If the values stored at the original memory location is modified, then it
 * is mutable.
 *
 * This is also related to whether it is "safe" to pass objects, because if
 * it is mutable, other classes can modify its value using that reference!
 *
 * @author Sophie Engle
 * @author CS 212 Software Development
 * @author University of San Francisco
 */
public class MutabilityDemo {

	/*
	 * This example is not about format strings! This is used to provide a
	 * consistent output format for easy comparison. The output will look like:
	 *
	 *  Before : HashCode : Value
	 *   Start : HashCode : Value
	 *     End : HashCode : Value
	 *   After : HashCode : Value
	 */
	public static final String format = "%7s : %08X : %s \n";

	public static void testPrimitive(int value) {
		System.out.println(" In Beg : " + value);
		value++;
		System.out.println(" In End : " + value);
	}

	/*
	 * Note the usage of System.identityHashCode(object) instead of
	 * object.hashCode(). This returns the value from the default hash code
	 * implementation, which gives the memory address of where the object is
	 * currently stored. Most classes override this implementation.
	 */

	public static void testWrapper(Integer value) {
		System.out.printf(format, " In Beg", System.identityHashCode(value), value);
		value = 1;
		System.out.printf(format, " In End", System.identityHashCode(value), value);
	}

	public static void testString(String value) {
		System.out.printf(format, " In Beg", System.identityHashCode(value), value);
		value = value.toUpperCase();
		System.out.printf(format, " In End", System.identityHashCode(value), value);
	}

	public static void testBuilder(StringBuilder value) {
		System.out.printf(format, " In Beg", System.identityHashCode(value), value);
		value.append(" banana");
		System.out.printf(format, " In End", System.identityHashCode(value), value);
	}

	public static void testArray(String[] value) {
		System.out.printf(format, " In Beg", System.identityHashCode(value), Arrays.toString(value));
		value[0] = "carrot";
		System.out.printf(format, " In End", System.identityHashCode(value), Arrays.toString(value));
	}

	public static void testList(ArrayList<String> value) {
		System.out.printf(format, " In Beg", System.identityHashCode(value), value);
		value.add("banana");
		System.out.printf(format, " In End", System.identityHashCode(value), value);
	}

	public static void main(String[] args) {

		/*
		 * Primitive types (and their wrapper classes) are immutable.
		 */

		int a = 0;
		System.out.println("Out Beg : " + a);
		testPrimitive(a);
		System.out.println("Out End : " + a);
		System.out.println();

		/*
		 * Objects in Java may be mutable -OR- immutable. Often, you can find
		 * this information in the API.
		 *
		 * When changing the value of an immutable object, you are actually
		 * creating a new object. The reference to the old object is broken
		 * and updated to the new object. The garbage collector will clean
		 * up the now unused space of the old object.
		 *
		 * Notice how the memory address changes once the Integer value is
		 * modified and reassigned! The original Integer object is not being
		 * modified.
		 */

		Integer b = new Integer(0);
		System.out.printf(format, "Out Beg", System.identityHashCode(b), b);
		testWrapper(b);
		System.out.printf(format, "Out End", System.identityHashCode(b), b);
		System.out.println();

		/*
		 * Strings are immutable objects! As such, their references are safe
		 * to pass since any modification to that String will break the
		 * reference to the original object.
		 */

		String c = "apple";
		System.out.printf(format, "Out Beg", System.identityHashCode(c), c);
		testString(c);
		System.out.printf(format, "Out End", System.identityHashCode(c), c);
		System.out.println();

		/*
		 * Because of this, if you need to make a lot of modification to a
		 * String, use a StringBuilder instead (or a StringBuffer if you are
		 * using multiple threads).
		 *
		 * StringBuilder is mutable, so the function may modify the value of
		 * the original object directly through the passed reference.
		 */

		StringBuilder d = new StringBuilder("apple");
		System.out.printf(format, "Out Beg", System.identityHashCode(d), d);
		testBuilder(d);
		System.out.printf(format, "Out End", System.identityHashCode(d), d);
		System.out.println();

		/*
		 * Arrays are mutable objects in Java, even if the array elements are
		 * a primitive type. Don't believe me? See it in action below!
		 */

		String[] e = new String[] {"apple", "banana"};
		System.out.printf(format, "Out Beg", System.identityHashCode(e), Arrays.toString(e));
		testArray(e);
		System.out.printf(format, "Out End", System.identityHashCode(e), Arrays.toString(e));
		System.out.println();

		/*
		 * The same holds for an ArrayList and other Collection objects. These
		 * objects are mutable, so passing a reference can be dangerous. A
		 * function could clear the collection completely through the reference.
		 */

		ArrayList<String> f = new ArrayList<String>();
		f.add("apple");
		System.out.printf(format, "Out Beg", System.identityHashCode(f), f);
		testList(f);
		System.out.printf(format, "Out End", System.identityHashCode(f), f);
	}

}
