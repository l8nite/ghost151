package edu.sjsu.cs.ghost151;

/**
 * The <b>Board</b> class represents the environment for the simulation. It
 * provides operations for getting and setting the objects at a particular
 * {@link edu.sjsu.cs.ghost151.BoardPosition}.
 */
public class Board {
	public final int ROWS;
	public final int COLUMNS;

	private BoardObject grid[][];
	private BoardRenderer renderer;

	/**
	 * Constructs and Initializes a 10x20 board
	 */
	public Board() {
		this(10, 20);
	}

	/**
	 * Constructs and Initializes the Board with the specified number of rows
	 * and columns. The smallest number of rows or columns is 2 (the Board would
	 * consist entirely of Walls)
	 */
	public Board(int numberOfRows, int numberOfColumns) {
		renderer = new StandardBoardRenderer();

		if (numberOfRows < 2 || numberOfColumns < 2) {
			throw new IndexOutOfBoundsException();
		}

		ROWS = numberOfRows;
		COLUMNS = numberOfColumns;

		Initialize();
	}

	/**
	 * Places Wall BoardObjects at the edges of the board and Empty BoardObjects
	 * everywhere within the interior
	 */
	public void Initialize() {
		grid = new BoardObject[ROWS][COLUMNS];

		for (int row = 0; row < ROWS; ++row) {
			for (int column = 0; column < COLUMNS; ++column) {
				BoardObject object = new BoardObject(BoardObjectType.Empty);

				// set all non-edge to empty, all edges to walls
				if (row == 0 || row == ROWS - 1 || column == 0
						|| column == COLUMNS - 1) {
					object.setType(BoardObjectType.Wall);
				}

				SetObjectAt(new BoardPosition(row, column, this), object);
			}
		}
	}

	/**
	 * Renders the board as an ASCII diagram: (Here, there are two Ghosts and
	 * one Target on the board).
	 * 
	 * <pre>
	 * ++++++++++++++++++++
	 * +                  +
	 * +         &        +
	 * +                  +
	 * +           &      +
	 * +                  +
	 * +                  +
	 * +  @               +
	 * +                  +
	 * ++++++++++++++++++++
	 * </pre>
	 */
	@Override
	public String toString() {
		return renderer.render(this);
	}

	/**
	 * Determine if the BoardObject at the given BoardPosition can be "moved on"
	 * (by a ghost, for example)
	 * 
	 * @param position
	 *            The BoardPosition to check
	 * @return true if the BoardObjectType at that position is Empty or Target
	 * @return false otherwise
	 */
	public boolean IsValidMoveTarget(BoardPosition position) {
		if (position.getBoard() != this) {
			return false;
		}

		BoardObjectType type = GetObjectAt(position).getType();

		if (type == BoardObjectType.Empty) {
			return true;
		}

		if (type == BoardObjectType.Target) {
			return true;
		}

		return false;
	}

	/**
	 * Takes any object and assigns it to a position on the Board.
	 * 
	 * @param boardPosition
	 *            the position to set the object at
	 * @param boardObject
	 *            the object to place
	 */
	public void SetObjectAt(BoardPosition boardPosition, BoardObject boardObject) {
		if (boardPosition.getBoard() != this) {
			return;
		}
		boardObject.setPosition(boardPosition);
		boardObject.setBoard(this);
		grid[boardPosition.getRow()][boardPosition.getColumn()] = boardObject;
	}

	/**
	 * get the object at a given Board position.
	 * 
	 * @param boardPosition
	 *            the board position to get object from
	 * @return the object at that position or null if position is out of range
	 */
	public BoardObject GetObjectAt(BoardPosition boardPosition) {
		if (boardPosition.getBoard() == this) {
			return grid[boardPosition.getRow()][boardPosition.getColumn()];
		} else {
			return null;
		}
	}

	/**
	 * Creates a new BoardPosition for this board
	 * 
	 * @param row
	 *            the row for the new position
	 * @param column
	 *            the column for the new position
	 * @return a new BoardPosition for this board
	 */
	public BoardPosition BoardPosition(int row, int column) {
		return new BoardPosition(row, column, this);
	}

	/**
	 * Get the grid object.
	 * 
	 * @return the grid
	 */
	public BoardObject[][] getGrid() {
		return grid;
	}

	/**
	 * @return the renderer this Board is using
	 */
	public BoardRenderer getRenderer() {
		return renderer;
	}

	/**
	 * @param renderer
	 *            the renderer this Board should use
	 */
	public void setRenderer(BoardRenderer renderer) {
		this.renderer = renderer;
	}
}
