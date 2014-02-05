
/**
 * This class briefly demonstrates the affect the "static" keyword has on
 * members/variables.
 *
 * <p><em>
 * Note that this class is designed to illustrate a specific concept, and
 * may not be an example of proper class design outside of this context.
 * </em></p>
 *
 * @see PrefixMap
 * @see PrefixDemo
 */
public class StaticDemo {

	private int a;
	private static int b;

	@SuppressWarnings("static-access")
	public static void main(String[] args) {

		StaticDemo d1 = new StaticDemo();
		d1.a = 0;
		d1.b = 1;

		System.out.println("d1.a = " + d1.a + ", d1.b = " + d1.b);

		StaticDemo d2 = new StaticDemo();
		d2.a = 2;
		d2.b = 3;

		System.out.println("d2.a = " + d2.a + ", d2.b = " + d2.b);
		System.out.println();

		// Since b is static, we also changed the value of d1.b!

		System.out.println("d1.a = " + d1.a + ", d2.b = " + d1.b);
		System.out.println();

		/*
		 * Note that I had to suppress warnings in this file. DON'T DO THIS!
		 * Since b is static, we should really be accessing it as below. It
		 * makes it more clear that we are dealing with a class-wide element
		 * instead of a per-instance element.
		 */

		StaticDemo.b = 4;

		System.out.println("d1.a = " + d1.a + ", d1.b = " + d1.b);
		System.out.println("d2.a = " + d2.a + ", d2.b = " + d2.b);
	}
}
