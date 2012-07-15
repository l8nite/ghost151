package edu.sjsu.cs.ghost151;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * The <b>LinearGhostMovementAlgorithm</b> scans linearly outward in all 8
 * directions from the Ghost and determines the nearest unexplored space in
 * those directions. If all spaces are explored, falls back to the
 * RandomGhostMovementAlgorithm. Ties are resolved randomly.
 */
public class LinearGhostMovementAlgorithm implements GhostMovementAlgorithm {

	/**
	 * See @link{edu.sjsu.cs.ghost151.LinearGhostMovementAlgorithm#
	 * DetermineNextPosition(edu.sjsu.cs.ghost151.Ghost, java.util.Random)}
	 */
	@Override
	public BoardPosition DetermineNextPosition(Ghost ghost) {
		return DetermineNextPosition(ghost, new Random());
	}

	/**
	 * Looks linearly outward from our position to determine the "nearest"
	 * unexplored space in a given direction.
	 * 
	 * Falls back to the RandomGhostMovementAlgorithm if it can't find one
	 * 
	 * @param ghost
	 *            The ghost to determine the next goal position for.
	 * @param generator
	 *            The random number generator to use
	 * 
	 * @return a position that we want to move towards
	 */
	@Override
	public BoardPosition DetermineNextPosition(Ghost ghost,
			Random generator) {
		BoardPosition targetPosition = ghost.getPosition();

		HashMap<BoardDirection, ScoredBoardPosition> scores = new HashMap<BoardDirection, ScoredBoardPosition>();
		ArrayList<ScoredBoardPosition> tiedChoices = new ArrayList<ScoredBoardPosition>();

		int minimumScore = Integer.MAX_VALUE;

		BoardDirection[] compassDirections = { BoardDirection.LEFT,
				BoardDirection.UP, BoardDirection.RIGHT, BoardDirection.DOWN,
				BoardDirection.LEFTDOWN, BoardDirection.LEFTUP,
				BoardDirection.RIGHTDOWN, BoardDirection.RIGHTUP };

		for (BoardDirection direction : compassDirections) {
			ScoredBoardPosition scoredPosition = LinearCountExploredPositions(
					ghost, direction);
			scores.put(direction, scoredPosition);

			int score = scoredPosition.getScore();

			if (score != -1 && score < minimumScore) {
				minimumScore = score;
				tiedChoices.clear();
				tiedChoices.add(scoredPosition);
			} else if (score != -1 && score == minimumScore) {
				tiedChoices.add(scoredPosition);
			}
		}

		if (tiedChoices.size() > 0) {
			int randomTieBreakerIndex = generator.nextInt(tiedChoices.size());
			targetPosition = tiedChoices.get(randomTieBreakerIndex);
		} else {
			return (GhostMovementAlgorithmType.RANDOM.CreateInstance()
					.DetermineNextPosition(ghost, generator));
		}

		return targetPosition;
	}

	/**
	 * Count the number of unexplored positions in a straight direction from our
	 * position
	 * 
	 * @param ghost
	 *            The ghost to count for
	 * @param direction
	 *            The direction to count
	 * 
	 * @return A "scored" BoardPosition where the score is the number of
	 *         explored spaces that would be traversed before reaching an
	 *         unexplored space. The score is -1 if there are no unexplored
	 *         spaces in the given direction
	 */
	private ScoredBoardPosition LinearCountExploredPositions(Ghost ghost, BoardDirection direction) {
		BoardPosition position = ghost.getPosition();

		int row = position.getRow();
		int column = position.getColumn();

		int dx = direction.getColumnOffset();
		int dy = direction.getRowOffset();

		int exploredPositionCount = 0;

		boolean[][] explored = ghost.getExploredPositions();

		Board board = ghost.getBoard();

		while ((row + dy) >= 0 && (row + dy) < board.ROWS && (column + dx) >= 0
				&& (column + dx) < board.COLUMNS) {
			row += dy;
			column += dx;

			if (explored[row][column] == false) {
				break;
			}

			++exploredPositionCount;
		}

		ScoredBoardPosition scoredBoardPosition = new ScoredBoardPosition(row,
				column, board, exploredPositionCount);

		// if we ended up hitting a boundary, then there are no unexplored
		// positions in this direction --
		if (explored[row][column] != false) {
			scoredBoardPosition.setScore(-1);
		}

		return scoredBoardPosition;
	}

}
