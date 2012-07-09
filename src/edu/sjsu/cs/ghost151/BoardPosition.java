package edu.sjsu.cs.ghost151;

/**
 * <b>BoardPosition</b> keeps track and informs of row and column information
 * per object placed on the Board.
 * 
 * @author Alben Cheung
 * @author MD Ashfaqul Islam
 * @author Shaun Guth
 * @author Jerry Phul
 */
public class BoardPosition {
	private int row;
	private int column;

	/**
	 * Holds the board position of the object.
	 * 
	 * @param row
	 *            the row position
	 * @param column
	 *            the column position
	 */
	public BoardPosition(int row, int column) {
		this.row = row;
		this.column = column;
	}

	/**
	 * Retrieve the row position of the object.
	 * 
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Set the row position of the object.
	 * 
	 * @param row
	 *            the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Retrieve the column position of the object.
	 * 
	 * @return the column position
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Set the column position of the object.
	 * 
	 * @param column
	 *            the column to set
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * Returns a string representation of this position.
	 */
	@Override
	public String toString() {
		return "(" + row + ", " + column + ")";
	}
}
