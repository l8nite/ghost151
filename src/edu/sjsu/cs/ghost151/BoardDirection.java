/**
 * 
 */
package edu.sjsu.cs.ghost151;

/**
 * @author shaung
 *
 */
public enum BoardDirection {
	STAYPUT(0,0),
	LEFT(-1,0),
	RIGHT(1,0),
	UP(0,-1),
	DOWN(0,1),
	LEFTUP(-1,-1),
	LEFTDOWN(-1,1),
	RIGHTUP(1,-1),
	RIGHTDOWN(1,1)
	;
	
	public static final int COLUMN_OFFSET_RIGHT = 1;
	public static final int COLUMN_OFFSET_LEFT = -1;
	public static final int COLUMN_OFFSET_STAYPUT = 0;
	
	public static final int ROW_OFFSET_DOWN = 1;
	public static final int ROW_OFFSET_UP = -1;
	public static final int ROW_OFFSET_STAYPUT = 0;
	
	private int columnOffset;
	private int rowOffset;
	
	private BoardDirection(int columnOffset, int rowOffset) {
		this.columnOffset = columnOffset;
		this.rowOffset = rowOffset;
	}
	
	public BoardPosition PositionFrom(BoardPosition position) {
		return new BoardPosition(position.getRow() + rowOffset, position.getColumn() + columnOffset);
	}

	/**
	 * @return the columnOffset
	 */
	public int getColumnOffset() {
		return columnOffset;
	}

	/**
	 * @param columnOffset the columnOffset to set
	 */
	public void setColumnOffset(int columnOffset) {
		this.columnOffset = columnOffset;
	}

	/**
	 * @return the rowOffset
	 */
	public int getRowOffset() {
		return rowOffset;
	}

	/**
	 * @param rowOffset the rowOffset to set
	 */
	public void setRowOffset(int rowOffset) {
		this.rowOffset = rowOffset;
	}
}
