/**
 * 
 */
package edu.sjsu.cs.ghost151;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author shaung
 * 
 */
public class NearestGhostMovementAlgorithm implements
		GhostMovementAlgorithm {

	/**
	 * See @link{edu.sjsu.cs.ghost151.LinearGhostMovementAlgorithm#
	 * DetermineNextPosition(edu.sjsu.cs.ghost151.Ghost, java.util.Random)}
	 */
	@Override
	public BoardPosition DetermineNextPosition(Ghost ghost) {
		return DetermineNextPosition(ghost, new Random());
	}

	/**
	 * Determines the nearest unexplored space on the grid and returns it.
	 * 
	 * @return a position that we want to move towards
	 */
	@Override
	public BoardPosition DetermineNextPosition(Ghost ghost, Random generator) {
		BoardPosition ghostPosition = ghost.getPosition();

		boolean[][] explored = ghost.getExploredPositions();

		ArrayList<ScoredBoardPosition> tiedChoices = new ArrayList<ScoredBoardPosition>();
		int minimumScore = Integer.MAX_VALUE;

		for (int row = 0; row < Board.ROWS; ++row) {
			for (int column = 0; column < Board.COLUMNS; ++column) {
				if (explored[row][column] == false) {
					// score this unexplored space as # of moves to reach it
					int rowDifference = Math.abs(ghostPosition.getRow() - row);
					int columnDifference = Math.abs(ghostPosition.getColumn()
							- column);

					int score = Math.max(rowDifference, columnDifference);

					if (score < minimumScore) {
						minimumScore = score;
						tiedChoices.clear();
						tiedChoices.add(new ScoredBoardPosition(row, column,
								score));
					} else if (score == minimumScore) {
						tiedChoices.add(new ScoredBoardPosition(row, column,
								score));
					}
				}
			}
		}

		if (tiedChoices.size() > 0) {
			int randomTieBreakerIndex = generator.nextInt(tiedChoices.size());
			return tiedChoices.get(randomTieBreakerIndex);
		} else {
			return ghostPosition;
		}
	}
}
