import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class demonstrates the Generic syntax, and how to use Generic types to
 * improve the generalization of your code.
 *
 * Please note this class is designed to illustrate a specific concept, and
 * is not an example of good class design outside of this context.
 *
 * @author Sophie Engle
 * @author CS 212 Software Development
 * @author University of San Francisco
 * @see <a href="http://docs.oracle.com/javase/tutorial/java/generics/upperBounded.html">Java Tutorials - Generics</a>
 */
public class GenericsDemo {

	/**
	 * This method demonstrates the problem with raw types.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void rawDemo() {
		/*
		 * You can use a "raw type" in your code, but it will generate
		 * quite a few warnings.
		 */
		ArrayList list = new ArrayList();
		list.add("alfalfa");
		list.add("bamboo");
		list.add("cactus");

		/*
		 * The warnings are there for a good reason. Without the type
		 * specified, we could place items in our list that don't match.
		 */
		// list.add(new Double(3.14));

		/*
		 * And, when we access the elements in our list, the reference will
		 * always be an Object. We have to downcast to access the actual
		 * String type, and this could cause runtime exceptions.
		 */
		for (Object element : list) {
			String casted = (String) element;
			System.out.println(casted.toUpperCase());
		}
	}

	/**
	 * This method demonstrates the benefit of Generics instead of raw types.
	 */
	public static void listDemo() {
		/*
		 * Instead of raw types, use the Generics notation to specify what
		 * type of object this ArrayList should store.
		 *
		 * You can initialize as follows:
		 * ArrayList<String> list = new ArrayList<String>();
		 *
		 * Starting in Java 7, you can simplify this to the line below. You
		 * can skip re-specifying the type in the < > angle brackets if Java
		 * can infer the type.
		 */
		ArrayList<String> list = new ArrayList<>();
		list.add("alfalfa");
		list.add("bamboo");
		list.add("cactus");

		/*
		 * Notice now, we are unable to add another object type to our list.
		 */
		// list.add(new Double(3.14));

		/*
		 * And, we no longer need to downcast since the ArrayList knows what
		 * type of objects it is storing!
		 */
		for (String element : list) {
			System.out.println(element.toUpperCase());
		}
	}

	/**
	 * This method demonstrates how we can use Generic types in a method to
	 * make that method more generalized. If we use upcasting and return an
	 * Object type, it has to be downcast later on back to its original type.
	 * This way, we always return the object in its own type without forcing
	 * casting (upcasting or downcasting) later on.
	 *
	 * @see #chooseRandom(Object, Object)
	 */
	public static void methodDemo() {
		String a = "alfalfa";
		String b = "bamboo";
		System.out.println(chooseRandom(a, b));
		System.out.println(chooseRandom(a, b));

		Integer c = 1;
		Integer d = 2;
		System.out.println(chooseRandom(c, d));
		System.out.println(chooseRandom(c, d));
	}

	/**
	 * This method is part of the {@link #methodDemo()} example.
	 *
	 * @param item1
	 * @param item2
	 * @return
	 * @see #methodDemo()
	 */
	public static <A> A chooseRandom(A item1, A item2) {
		if (Math.random() > 0.5) {
			return item1;
		}
		else {
			return item2;
		}
	}

	/**
	 * This method demonstrates how we can replace restrictions on the
	 * Generic type if necessary.
	 *
	 * @see #chooseMax(Comparable, Comparable)
	 */
	public static void comparableDemo() {
		String a = "alfalfa";
		String b = "bamboo";
		System.out.println(chooseMax(a, b));

		Integer c = 1;
		Integer d = 2;
		System.out.println(chooseMax(c, d));
	}

	/**
	 * This method is part of the {@link #comparableDemo()} example.
	 *
	 * @param item1
	 * @param item2
	 * @return
	 * @see #comparableDemo()
	 */
	public static <B extends Comparable<B>> B chooseMax(B item1, B item2) {
		if (item1.compareTo(item2) > 0) {
			return item1;
		}
		else {
			return item2;
		}
	}

	/**
	 * This method demonstrates how we can create generalized classes using
	 * Generic types as well, allowing the developer to specify the type of
	 * object or objects that should be used by that class.
	 *
	 * @see #Pair
	 */
	public static void classDemo() {
		Pair<String, String> pair1 = new Pair<>("a", "alfalfa");
		Pair<Double, String> pair2 = new Pair<>(Math.PI, "PI");

		System.out.println(pair1);
		System.out.println(pair2);
	}

	/**
	 * This class is part of the {@link GenericsDemo#classDemo()} example.
	 * @param <K> key type
	 * @param <V> value type
	 * @see GenericsDemo#classDemo()
	 */
	public static class Pair<K, V> {
		private final K one;
		private final V two;

		public Pair(K one, V two) {
			this.one = one;
			this.two = two;
		}

		@Override
		public String toString() {
			return "(" + one + ", " + two + ")";
		}
	}

	/**
	 * This method demonstrates how to use wildcards to bound the generic
	 * type to a {@link Number}, and use upcasting.
	 *
	 * @see #sumNumbers1(Collection)
	 * @see #sumNumbers2(Collection)
	 */
	public static void wildcardDemo() {
		List<Number> nums = new ArrayList<>();
		nums.add(new Double(3.14));
		nums.add(new Integer(42));
		System.out.println(sumNumbers1(nums));
		System.out.println(sumNumbers2(nums));

		List<Integer> ints = new ArrayList<>();
		ints.add(new Integer(1));
		ints.add(new Integer(2));
		System.out.println(sumNumbers1(ints));

		/*
		 * With just upcasting alone, we will get compile errors when
		 * we try the following line. This is because while Integer is
		 * a subclass of Number, List<Integer> is *NOT* a subclass of
		 * List<Number>. Since they are not subclasses of each other,
		 * upcasting will fail.
		 */
		// System.out.println(sumNumbers2(ints));
	}

	/**
	 * This method is part of the {@link #wildcardDemo()} example.
	 *
	 * @param nums
	 * @return
	 * @see #wildcardDemo()
	 */
	public static double sumNumbers1(Collection<? extends Number> nums) {
		double sum = 0.0;

		for (Number n : nums) {
			sum += n.doubleValue();
		}

		return sum;
	}

	/**
	 * This method is part of the {@link #wildcardDemo()} example.
	 *
	 * @param nums
	 * @return
	 * @see #wildcardDemo()
	 */
	public static double sumNumbers2(Collection<Number> nums) {
		double sum = 0.0;

		for (Number n : nums) {
			sum += n.doubleValue();
		}

		return sum;
	}

	public static void main(String[] args) {
		rawDemo();
		listDemo();
		methodDemo();
		comparableDemo();
		classDemo();
		wildcardDemo();
	}
}
