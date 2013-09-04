/**
 * This demo briefly illustrates some basic terminology for
 * object-oriented programming. It consists of two classes:
 *
 * 	   - Widget.java
 *     - WidgetDemo.java
 *
 * @author Sophie Engle
 * @author CS 212 Software Development
 * @author University of San Francisco
 */

public class WidgetDemo {

	public static void main(String[] args) {
		/*
		 * TERM: STATIC
		 * Since this method is static, we can access it when
		 * there are no instances created.
		 */
		System.out.println(Widget.numWidgets());

		/*
		 * You can access and directly set public static members anytime.
		 */
		Widget.debug = true;

		/*
		 * TERM: INSTANCE
		 * The following are actual objects, or instances of
		 * the Widget class. They have their own instance
		 * attributes, but share class attributes.
		 *
		 * TERM: DECLARATION
		 * We have declared the identifiers for these elements,
		 * but memory has not yet been reserved. Any access
		 * will result in a null pointer exception.
		 */
		Widget widget1;
		Widget widget2;

		/*
		 * TERM: DEFINIITION
		 * We are reserving memory with the "new" keyword here,
		 * creating actual objects with initial values.
		 */
		widget1 = new Widget("A");
		widget2 = new Widget("B");

		// This uses the toString() method automatically.
		System.out.println(widget1);
		System.out.println(widget2);

		// Note our static member has updated properly.
		System.out.println(Widget.numWidgets());

		// We are unable to modify the name to an invalid value.
		widget1.setName(null);
		System.out.println(widget1);
	}
}
