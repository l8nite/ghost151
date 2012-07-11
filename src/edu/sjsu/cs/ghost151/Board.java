package edu.sjsu.cs.ghost151;

/**
 * The <b>Board</b> class is a singleton which represents the environment for
 * the simulation. It provides operations for getting and setting the objects at
 * a particular {@link edu.sjsu.cs.ghost151.BoardPosition}.
 */
public enum Board {
	/**
	 * Board is a singleton, use Board.INSTANCE to get the instance.
	 */
	INSTANCE;

	/**
	 * The number of rows this board contains
	 */
	public static final int ROWS = 10;

	/**
	 * The number of columns this board contains.
	 */
	public static final int COLUMNS = 20;

	private BoardObject grid[][];
	private BoardRenderer renderer;

	/**
	 * Constructs and Initializes the Board
	 */
	private Board() {
		Initialize();
		renderer = new StandardBoardRenderer();
	}

	/**
	 * Places Wall BoardObjects at the edges of the board and Empty BoardObjects
	 * in the interior
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

				SetObjectAt(new BoardPosition(row, column), object);
			}
		}
	}

	/**
	 * Renders the board as an ASCII diagram:
	 * (Here, there are two Ghosts and one Target on the board).
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
	 * Determine if this BoardObject can be "moved on" (by a ghost, for example)
	 * 
	 * @return true if the BoardObjectType is Empty or Target
	 * @return false otherwise
	 */
	public boolean IsValidMoveTarget(BoardPosition position) {
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
		boardObject.setPosition(boardPosition);
		grid[boardPosition.getRow()][boardPosition.getColumn()] = boardObject;
	}

	/**
	 * get the object at a given Board position.
	 * 
	 * @param boardPosition
	 *            the board position to get object from
	 */
	public BoardObject GetObjectAt(BoardPosition boardPosition) {
		int row = boardPosition.getRow();
		int column = boardPosition.getColumn();

		if (row >= 0 && row < Board.ROWS && column >= 0
				&& column < Board.COLUMNS) {
			return grid[row][column];
		} else {
			return null;
		}
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
	 * @return the renderer
	 */
	public BoardRenderer getRenderer() {
		return renderer;
	}

	/**
	 * @param renderer the renderer to set
	 */
	public void setRenderer(BoardRenderer renderer) {
		this.renderer = renderer;
	}
}
