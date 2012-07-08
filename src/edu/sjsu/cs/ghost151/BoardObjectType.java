package edu.sjsu.cs.ghost151;

/**
 * <b>BoardObjectType</b> responsible for keeping track of surroundings of an
 * object and notifying the Board object. Typical surrounding objects to be
 * found are empty spaces, Wall, PacMan, and Ghosts.
 * 
 * @author Alben Cheung
 * @author MD Ashfaqul Islam
 * @author Shaun Guth
 * @author Jerry Phul
 */
public enum BoardObjectType {
	Empty(" "), Target("@"), Wall("+"), Ghost("&");

	private final String representation;

	/**
	 * Establish a text representation of the object on the Board.
	 * 
	 * @param representation
	 *            the representation to use
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
