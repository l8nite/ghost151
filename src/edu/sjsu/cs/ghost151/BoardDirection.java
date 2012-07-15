package edu.sjsu.cs.ghost151;

/**
 * The <b>BoardDirection</b> class stores a pair of row/column offsets that
 * indicate an ordinal direction on the Board grid.
 * 
 * The class also contains a number of helpful static attributes for the 8
 * compass directions, plus a "STAYPUT" direction that has 0 offsets.
 */
public enum BoardDirection {
	LEFTUP(-1, -1), UP(0, -1), RIGHTUP(1, -1), LEFT(-1, 0), STAYPUT(0, 0), RIGHT(
			1, 0), LEFTDOWN(-1, 1), DOWN(0, 1), RIGHTDOWN(1, 1);

	private final int rowOffset;
	private final int columnOffset;

	/**
	 * Creates a new BoardDirection with the given row and column offsets
	 * 
	 * @param rowOffset
	 * @param columnOffset
	 */
	private BoardDirection(int columnOffset, int rowOffset) {
		this.rowOffset = rowOffset;
		this.columnOffset = columnOffset;
	}

	/**
	 * @return the columnOffset for this direction
	 */
	public int getColumnOffset() {
		return columnOffset;
	}

	/**
	 * @return the rowOffset for this direction
	 */
	public int getRowOffset() {
		return rowOffset;
	}

	/**
	 * @return an array of the compass BoardDirections in left to right, top to
	 *         bottom order
	 */
	public static BoardDirection[] CompassDirections() {
		BoardDirection[] compass = { LEFTUP, UP, RIGHTUP, LEFT, RIGHT,
				LEFTDOWN, DOWN, RIGHTDOWN };
		return compass;
	}

	/**
	 * Calculates a new BoardPosition based on a given position and this
	 * Direction
	 * 
	 * @param position
	 *            the "from" position
	 * @return the BoardPosition in our direction from the given position
	 */
	public BoardPosition PositionFrom(BoardPosition position) {
		BoardPosition newPosition = null;

		try {
			newPosition = new BoardPosition(position.getRow() + rowOffset,
					position.getColumn() + columnOffset, position.getBoard());
		} catch (IndexOutOfBoundsException indexOutOfBoundsException) {
		}

		return newPosition;
	}

	/**
	 * Returns a BoardDirection from the given offsets
	 * 
	 * @param columnOffset
	 *            An integer representing the column offset
	 * @param rowOffset
	 *            An integer representing the row offset
	 * @return The BoardDirection representing the unit-scaled offsets
	 */
	public static BoardDirection FromOffsets(int columnOffset, int rowOffset) {
		if (columnOffset < 0) {
			if (rowOffset < 0) {
				return LEFTUP;
			} else if (rowOffset == 0) {
				return LEFT;
			} else {
				return LEFTDOWN;
			}
		} else if (columnOffset == 0) {
			if (rowOffset < 0) {
				return UP;
			} else if (rowOffset == 0) {
				return STAYPUT;
			} else {
				return DOWN;
			}
		} else {
			if (rowOffset < 0) {
				return RIGHTUP;
			} else if (rowOffset == 0) {
				return RIGHT;
			} else {
				return RIGHTDOWN;
			}
		}
	}

	/**
	 * Returns a BoardDirection representing the horizontal component of the
	 * current BoardDirection
	 * 
	 * @return the horizontal component BoardDirection
	 */
	public BoardDirection HorizontalComponent() {
		if (this == UP || this == DOWN || this == STAYPUT) {
			return STAYPUT;
		}

		if (this == LEFTUP || this == LEFTDOWN) {
			return LEFT;
		}

		if (this == RIGHTUP || this == RIGHTDOWN) {
			return RIGHT;
		}

		// LEFT, RIGHT
		return this;
	}

	/**
	 * Returns a BoardDirection representing the vertical component of the
	 * current BoardDirection
	 * 
	 * @return the vertical component BoardDirection
	 */
	public BoardDirection VerticalComponent() {
		if (this == LEFT || this == RIGHT || this == STAYPUT) {
			return STAYPUT;
		}

		if (this == LEFTUP || this == RIGHTUP) {
			return UP;
		}

		if (this == LEFTDOWN || this == RIGHTDOWN) {
			return DOWN;
		}

		// UP, DOWN
		return this;
	}
}
