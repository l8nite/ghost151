package edu.sjsu.cs.ghost151;

/**
 * Renders the Board instance in the "standard" way, which means that each
 * object is represented by its type representation.
 */
public class StandardBoardRenderer implements BoardRenderer {

	@Override
	public String render(Board board) {
		StringBuilder sb = new StringBuilder();
		BoardObject[][] grid = board.getGrid();

		for (int row = 0; row < board.ROWS; ++row) {
			for (int column = 0; column < board.COLUMNS; ++column) {
				BoardObject object = grid[row][column];
				sb.append(object.toString());
			}

			sb.append("\n");
		}

		return sb.toString();
	}

}
