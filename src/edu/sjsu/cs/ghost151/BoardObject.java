package edu.sjsu.cs.ghost151;

/**
 * <b>BoardObject</b> determines the type of object placed in the Board and
 * notifies the Board object. It will also get and set an object's position on
 * the board.
 * 
 * @author Alben Cheung
 * @author MD Ashfaqul Islam
 * @author Shaun Guth
 * @author Jerry Phul
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
	 * Determine if this BoardObject can be "moved on" (by a ghost, for example)
	 * @return true if the BoardObjectType is Empty or Target
	 * @return false otherwise
	 */
	public boolean IsValidMoveTarget() {
		if (type == BoardObjectType.Empty) {
			return true;
		}
		
		if (type == BoardObjectType.Target) {
			return true;
		}
		
		return false;
	}

	/**
	 * Return the position of the object.
	 * 
	 * @return the position
	 */
	/**
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
	 * Set the type of the current board object.
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
