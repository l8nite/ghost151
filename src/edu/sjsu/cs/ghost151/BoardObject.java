/**
 * 
 */
package edu.sjsu.cs.ghost151;

/**
 * @author shaung
 *
 */
public class BoardObject {

	private BoardPosition position;
	private BoardObjectType type;
	protected Board board;
	
	public BoardObject(BoardObjectType type)
	{
		board = Board.INSTANCE;
		setType(type);
	}
	
	/**
	 * @return the position
	 */
	public BoardPosition getPosition() 
	{
		return position;
	}
	
	/**
	 * @param position the position to set
	 */
	public void setPosition(BoardPosition position) 
	{
		this.position = position;
	}
	
	/**
	 * @return the type
	 */
	public BoardObjectType getType() 
	{
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(BoardObjectType type) 
	{
		this.type = type;
	}
	
	/**
	 * Renders this board object's type representation
	 * @return the string representation of this object
	 */
	public String toString()
	{
		return type.toString(); 
	}
}
