package edu.sjsu.cs.ghost151;

/**
 * A <b>BoardObject</b> is the basic unit stored within the Board.
 * 
 * It stores information about its type and position and provides a mechanism
 * for retrieving its String representation
 */
public class BoardObject {

	protected BoardPosition position;
	private BoardObjectType type;
	protected Board board;

	/**
	 * Create an object and establish it's type.
	 * 
	 * @param type
	 *            the type the object should be set to
	 */
	public BoardObject(BoardObjectType type) {
		board = Board.INSTANCE;
		setType(type);
	}

	/**
	 * Return the position of the board object.
	 * 
	 * @return the position
	 */
	public BoardPosition getPosition() {
		return position;
	}

	/**
	 * Set the position of the board object.
	 * 
	 * @param position
	 *            the position to set
	 */
	public void setPosition(BoardPosition position) {
		this.position = position;
	}

	/**
	 * Return the type of the board object.
	 * 
	 * @return the type
	 */
	public BoardObjectType getType() {
		return type;
	}

	/**
	 * Set the type of the board object.
	 * 
	 * @param type
	 *            the type to set
	 */
	public void setType(BoardObjectType type) {
		this.type = type;
	}

	/**
	 * Renders this board object's type representation
	 * 
	 * @return the string representation of this object
	 */
	public String toString() {
		return type.toString();
	}
}
