package edu.sjsu.cs.ghost151;

/**
 * <b>BoardPosition</b> is a simple utility class storing row/column coordinates
 * on the Board.
 */
public class BoardPosition {
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
	 * Compares two BoardPositions to see if they're referencing the same
	 * coordinates
	 * 
	 * @param that
	 *            The object to compare with
	 * @return true if both BoardPositions have the same row/column, false
	 *         otherwise
	 */
	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (!(that instanceof BoardPosition)) {
			return false;
		}

		BoardPosition position = (BoardPosition) that;

		return position.getRow() == this.row
				&& position.getColumn() == this.column;
	}

	/**
	 * Determine a BoardDirection that gets us closer to this position from the
	 * given position (given the constraints of the board (edges, etc)).
	 * 
	 * @param position
	 *            the position we want to move from
	 * @return the direction the ghost should move to get to the current
	 *         position
	 */
	public BoardDirection DirectionFrom(BoardPosition position) {
		BoardPosition targetPosition = this;

		return new BoardDirection(targetPosition.getRow() - position.getRow(),
				targetPosition.getColumn() - position.getColumn());
	}
}
