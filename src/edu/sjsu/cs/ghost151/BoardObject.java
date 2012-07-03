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
	/**
	 * @return the position
	 */
	public BoardPosition getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(BoardPosition position) {
		this.position = position;
	}
	/**
	 * @return the type
	 */
	public BoardObjectType getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(BoardObjectType type) {
		this.type = type;
	}
	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}
	/**
	 * @param board the board to set
	 */
	public void setBoard(Board board) {
		this.board = board;
	}
	
	
}
