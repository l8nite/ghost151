package edu.sjsu.cs.ghost151;

/**
 * The <b>BoardDirection</b> class stores a pair of row/column offsets that
 * indicate an ordinal direction on the Board grid.
 * 
 * The class also contains a number of helpful static attributes for the 8
 * compass directions, plus a "STAYPUT" direction that has 0 offsets.
 */
public class BoardDirection {
	public static final BoardDirection STAYPUT = new BoardDirection(0, 0);
	public static final BoardDirection LEFT = new BoardDirection(-1, 0);
	public static final BoardDirection RIGHT = new BoardDirection(1, 0);
	public static final BoardDirection UP = new BoardDirection(0, -1);
	public static final BoardDirection DOWN = new BoardDirection(0, 1);
	public static final BoardDirection LEFTUP = new BoardDirection(-1, -1);
	public static final BoardDirection LEFTDOWN = new BoardDirection(-1, 1);
	public static final BoardDirection RIGHTUP = new BoardDirection(1, -1);
	public static final BoardDirection RIGHTDOWN = new BoardDirection(1, 1);

	private final int rowOffset;
	private final int columnOffset;

	/**
	 * Creates a new BoardDirection with the given row and column offsets
	 * 
	 * @param rowOffset
	 * @param columnOffset
	 */
	public BoardDirection(int rowOffset, int columnOffset) {
		if (rowOffset != 0) {
			rowOffset /= Math.abs(rowOffset);
		}

		this.rowOffset = rowOffset;

		if (columnOffset != 0) {
			columnOffset /= Math.abs(columnOffset);
		}

		this.columnOffset = columnOffset;
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
		return new BoardPosition(position.getRow() + rowOffset,
				position.getColumn() + columnOffset);
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
		BoardDirection[] compass = { BoardDirection.LEFTUP, BoardDirection.UP,
				BoardDirection.RIGHTUP, BoardDirection.LEFT,
				BoardDirection.RIGHT, BoardDirection.LEFTDOWN,
				BoardDirection.DOWN, BoardDirection.RIGHTDOWN, };

		return compass;
	}
}
