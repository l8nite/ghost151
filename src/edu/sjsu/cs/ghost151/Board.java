package edu.sjsu.cs.ghost151;

/**
 * The Board class is a singleton which represents the environment for the
 * simulation.  It provides operations for getting and setting the objects
 * at particular @link{BoardPosition}s.
 */
public enum Board {
	INSTANCE; // singleton

	public static final int ROWS = 10;
	public static final int COLUMNS = 20;

	private BoardObject grid[][];

	/**
	 * Constructs the default Board object.
	 */
	private Board() {
		Initialize();
	}

	/**
	 * Initializes empty board, sets the walls to the outer edges
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
	 * Convert to string.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int row = 0; row < ROWS; ++row) {
			for (int column = 0; column < COLUMNS; ++column) {
				BoardObject object = grid[row][column];

				boolean GHOSTVISION = false;
				if (GHOSTVISION) {
					if (object.getType() == BoardObjectType.Target
							|| object.getType() == BoardObjectType.Ghost) {
						sb.append(object.toString());
					} else {
						boolean isExplored = false;
						for (Ghost ghost : Game.INSTANCE.getGhosts()) {
							boolean[][] explored = ghost.getExploredPositions();
							if (explored[row][column] != false) {
								sb.append(object.toString());
								isExplored = true;
								break;
							}
						}

						if (!isExplored) {
							sb.append("#");
						}
					}
				} else {
					sb.append(object.toString());
				}
			}

			sb.append("\n");
		}

		return sb.toString();
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
}
