/**
 * This demo briefly illustrates some basic terminology for
 * object-oriented programming.
 *
 * <p><em>
 * Note that this class is designed to illustrate a specific concept, and
 * may not be an example of proper class design outside of this context.
 * </em></p>
 *
 * @see Widget
 * @see WidgetDemo
 */

/*
 * TERM: CLASS
 * Defines the implementation of an OBJECT.
 */
public class Widget {

	/*
	 * TERM: INSTANCE MEMBER
	 * Each instance will have its own value. Initialization
	 * should occur in the constructor. (If you only use this
	 * value in a single method, it should be a local variable
	 * instead of an instance member.)
	 *
	 * TERM: PRIVATE
	 * Only this class can access this value. In general, you
	 * should make members private unless you have a reason.
	 *
	 * TERM: IDENTIFIER
	 * The name of something, in this case the name of the
	 * instance member is "widgetName".
	 *
	 * TERM: DECLARATION
	 * Establishes an identifier and associated attributes
	 * (such as the type).
	 */
	private String widgetName;

	/*
	 * TERM: FINAL
	 * Value may not change after initialization. Should
	 * initialize at declaration, or in constructor.
	 */
	private final int widgetID;

	/*
	 * TERM: STATIC MEMBER/CLASS MEMBER
	 * All instances of this class share this value. Should
	 * initialize at declaration.
	 *
	 * TERM: DEFINITION
	 * A declaration that also reserves storage (for data) or
	 * provides an implementation (for methods).
	 */
	private static int numWidgets = 0;

	/*
	 * TERM: PUBLIC
	 * Any other class may directly modify this value.
	 */
	public static boolean debug = false;

	/*
	 * TERM: CONSTRUCTOR
	 * The method that is called when an object/instance is
	 * created. Should initialize all of the instance members.
	 */
	public Widget(String name) {
		/*
		 * TERM: THIS
		 * The "this" keyword refers to the current instance.
		 * Here, we set this object's widget name to whatever
		 * was passed in to the constructor.
		 */
		this.widgetName = name;

		/*
		 * TERM: STATIC (ACCESS)
		 * We access static members through the class name,
		 * to make it clear this changes the value for all
		 * instances of this class.
		 *
		 * TERM: FINAL
		 * After this initialization, the value of widgetID
		 * may not change. (Try it!)
		 */
		this.widgetID = ++numWidgets;

		if (debug) {
			System.out.println("Created widget #" + widgetID + " named " +
					widgetName + ".");
		}
	}

	/*
	 * TERM: OVERLOADING
	 * You can provide methods with the same name, but different parameters.
	 * They should do essentially the same thing! A good way to ensure this
	 * is to call the other overloaded method from this one.
	 *
	 * TERM: DEFAULT CONSTRUCTOR
	 * A constructor that takes no parameters. It is usually a good idea to
	 * create a default constructor.
	 */
	public Widget() {
		/*
		 * TERM: THIS
		 * You can use the "this" keyword as a method too, which will call
		 * the appropriate constructor. This lets you reuse the initialization
		 * code you have written in a different constructor.
		 */
		this("Widget");
	}

	/*
	 * TERM: STATIC METHOD/CLASS METHOD
	 * A static method may not access any instance members.
	 * The method itself does not require an instance to be
	 * created, and can be accessed through the class name.
	 *
	 * If your method does not access any instance members,
	 * make sure you make it static for a bit of speedup.
	 *
	 * TERM: GETTER/GET METHOD
	 * Since this member is private, other classes cannot
	 * access this value (an example of ENCAPSULATION). We
	 * do not want other classes to be able to change this
	 * value, but it is okay for them to see what the value
	 * is currently.
	 */
	public static int numWidgets() {
		return Widget.numWidgets;
	}

	/*
	 * TERM: NON-STATIC METHOD/INSTANCE METHOD
	 * A non-static method can only be accessed through an
	 * actual instance.
	 */
	public String getName() {
		return this.widgetName;
	}

	/*
	 * TERM: HELPER METHOD/PRIVATE METHOD
	 * These types of methods are not visible outside of the
	 * class, and are meant to "help" other public methods
	 * in the class.
	 */
	private static boolean validName(String name) {
		return name != null && !name.trim().isEmpty();
	}

	/*
	 * TERM: SETTER/SET METHOD
	 * These types of methods allow you to validate unsafe
	 * input before changing the value. In this case, we make
	 * sure the name is not null or empty.
	 *
	 * Often times, a set method returns a boolean to indicate
	 * whether the change was made.
	 */
	public boolean setName(String newName) {
		/*
		 * TERM: LOCAL VARIABLE
		 * This variable only exists locally, inside this
		 * method. It isn't an "attribute" of this object
		 * so it does not make sense to make it a member.
		 */
		boolean valid = false;

		if (validName(newName)) {
			this.widgetName = newName;
			valid = true;
		}

		if (debug) {
			System.out.println("Failed to change name for widget #" +
					widgetID + ".");
		}

		return valid;
	}

	/*
	 * TERM: OVERRIDING
	 * This replaces or overrides the implementation of
	 * toString() provided by the Object class. More on this
	 * when we discuss inheritance.
	 */
	@Override
	public String toString() {
		return widgetName + " (" + widgetID + ")";
	}
}
