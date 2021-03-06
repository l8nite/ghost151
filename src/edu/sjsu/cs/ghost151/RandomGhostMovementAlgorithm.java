package edu.sjsu.cs.ghost151;

import java.util.ArrayList;
import java.util.Random;

/**
 * The <b>RandomGhostMovementAlgorithm</b> chooses a random unexplored space and
 * moves towards it.
 */
public class RandomGhostMovementAlgorithm implements GhostMovementAlgorithm {

	/**
	 * See @link{edu.sjsu.cs.ghost151.RandomGhostMovementAlgorithm#
	 * DetermineNextPosition(edu.sjsu.cs.ghost151.Ghost, java.util.Random)}
	 */
	@Override
	public BoardPosition DetermineNextPosition(Ghost ghost) {
		return DetermineNextPosition(ghost, new Random());
	}

	/**
	 * Randomly chooses one of the unexplored spaces on the grid and returns it
	 * 
	 * @param ghost
	 *            The ghost to choose for
	 * @param generator
	 *            The Random generator to use
	 * @return The position to move towards
	 */
	public BoardPosition DetermineNextPosition(Ghost ghost, Random generator) {
		BoardPosition targetPosition = ghost.getPosition();

		boolean[][] explored = ghost.getExploredPositions();

		// pick a remaining unexplored space at random
		ArrayList<BoardPosition> unexplored = new ArrayList<BoardPosition>();

		Board board = ghost.getBoard();
		for (int row = 0; row < board.ROWS; ++row) {
			for (int column = 0; column < board.COLUMNS; ++column) {
				if (explored[row][column] == false) {
					unexplored.add(board.BoardPosition(row, column));
				}
			}
		}

		if (unexplored.size() > 0) {
			int randomUnexploredIndex = generator.nextInt(unexplored.size());
			targetPosition = unexplored.get(randomUnexploredIndex);
		}

		return targetPosition;
	}

}
