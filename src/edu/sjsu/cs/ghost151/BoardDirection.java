/**
 * 
 */
package edu.sjsu.cs.ghost151;

/**
 * @author shaung
 * 
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

	public BoardPosition PositionFrom(BoardPosition position) {
		return new BoardPosition(position.getRow() + rowOffset,
				position.getColumn() + columnOffset);
	}

	/**
	 * @return the columnOffset
	 */
	public int getColumnOffset() {
		return columnOffset;
	}

	/**
	 * @return the rowOffset
	 */
	public int getRowOffset() {
		return rowOffset;
	}
	
	/**
	 * @return an array of the compass BoardDirections
	 */
	public static BoardDirection[] CompassDirections() {
		BoardDirection[] compass = {
			BoardDirection.LEFTUP,
			BoardDirection.UP,
			BoardDirection.RIGHTUP,
			BoardDirection.LEFT,
			BoardDirection.RIGHT, 
			BoardDirection.LEFTDOWN, 
			BoardDirection.DOWN,
			BoardDirection.RIGHTDOWN, 
		};
		
		return compass;
	}
}
