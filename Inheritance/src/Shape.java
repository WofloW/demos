/**
 * Demonstrates several inheritance concepts, including basic inheritance,
 * upcasting and downcasting, nested classes, and anonymous classes.
 *
 * @see {@link ShapeDemo}
 * @see {@link Shape}
 * @see {@link Rectangle}
 * @see {@link Square}
 *
 * <p><em>
 * Note that this class is designed to illustrate a specific concept, and
 * may not be an example of proper class design outside of this context.
 * </em></p>
 */
public abstract class Shape {
	/** Stores the name of the shape. */
	protected String shapeName;

	/**
	 * Default constructor. Sets name of shape to "Shape".
	 */
	public Shape() {
		this("Shape");
	}

	/**
	 * Constructor. Requires name of shape.
	 * @param shapeName name of shape
	 */
	public Shape(String shapeName) {
		this.shapeName = shapeName;
	}

	/**
	 * Calculates and returns the area of the shape.
	 * @return area of shape
	 */
	public abstract double area();

	/**
	 * Returns the name (circle, square, etc.) of the shape.
	 * @return name of shape
	 */
	public String getType() 	{
		return this.shapeName;
	}

	/**
	 * Returns a <code>String</code> representation of this object.
	 * @return <code>String</code> representation of object
	 */
	@Override
	public String toString() {
		return getType();
	}
}
