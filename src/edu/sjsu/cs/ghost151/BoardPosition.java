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
public class BoardPosition implements Comparable<BoardPosition> {
	protected int row;
	protected int column;

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

	/**
	 * Compares two positions
	 * 
	 * "less than" another position if it is higher or left in the grid
	 * "greater than" otherwise
	 */
	@Override
	public int compareTo(BoardPosition position) {
		if (position.getRow() == row && position.getColumn() == column) {
			return 0;
		} else if (position.getRow() < row || position.getColumn() < column) {
			return -1;
		} else {
			return 1;
		}
	}
	
	/**
	 * Determine a BoardDirection that gets us closer to this position from
	 * the given position (given the constraints of the board (edges, etc)).
	 * 
	 * @param position
	 *            the position we want to move from
	 * @return the direction the ghost should move to get to the current position
	 */
	public BoardDirection DirectionFrom(BoardPosition position) {
		BoardPosition targetPosition = this;

		// and then move towards it
		BoardDirection moveDirection = BoardDirection.STAYPUT;

		if (targetPosition.getRow() > position.getRow()) {
			moveDirection.setRowOffset(BoardDirection.ROW_OFFSET_DOWN);
		} else if (targetPosition.getRow() < position.getRow()) {
			moveDirection.setRowOffset(BoardDirection.ROW_OFFSET_UP);
		}

		if (targetPosition.getColumn() > position.getColumn()) {
			moveDirection.setColumnOffset(BoardDirection.COLUMN_OFFSET_RIGHT);
		} else if (targetPosition.getColumn() < position.getColumn()) {
			moveDirection.setColumnOffset(BoardDirection.COLUMN_OFFSET_LEFT);
		}

		return moveDirection;
	}
}
