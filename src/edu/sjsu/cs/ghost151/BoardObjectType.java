package edu.sjsu.cs.ghost151;

/**
 * <b>BoardObjectType</b> is an enumeration of the potential kinds of objects
 * that may be stored on a Board grid. These include empty spaces, targets,
 * walls, and ghosts.
 */
public enum BoardObjectType {
	Empty(" "), Target("@"), Wall("+"), Ghost("&");

	private final String representation;

	/**
	 * Create a new BoardObjectType with the given representation.
	 * 
	 * @param representation
	 *            the single-character representation to use
	 */
	private BoardObjectType(String representation) {
		this.representation = representation;
	}

	/**
	 * Return the object representation as a string.
	 * 
	 * @return the object as a string representation
	 */
	public String toString() {
		return representation;
	}
}
