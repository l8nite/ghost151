package edu.sjsu.cs.ghost151;

import java.util.ArrayList;

/**
 * Renders the Board in "Ghost Vision" - which masks any spaces not explored by
 * the ghosts in the game with a hash mark, except for the Target and the Ghosts
 * themselves
 */
public class GhostVisionBoardRenderer implements BoardRenderer {

	@Override
	public String render(Board board) {
		StringBuilder sb = new StringBuilder();
		BoardObject[][] grid = board.getGrid();
		
		ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
		
		for (BoardObject[] row : grid) {
			for (BoardObject object : row) {
				if (object.getType() == BoardObjectType.Ghost) {
					ghosts.add((Ghost)object);
				}
			}
		}

		for (int row = 0; row < board.ROWS; ++row) {
			for (int column = 0; column < board.COLUMNS; ++column) {
				BoardObject object = grid[row][column];

				if (object.getType() == BoardObjectType.Target
						|| object.getType() == BoardObjectType.Ghost) {
					sb.append(object.toString());
				} else {
					boolean isExplored = false;
					for (Ghost ghost : ghosts) {
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
			}

			sb.append("\n");
		}

		return sb.toString();
	}
}
