/**
 * 
 */
package edu.sjsu.cs.ghost151;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinearGhostMovementAlgorithmTest {

	@Test
	public void testLinearGhostMovementAlgorithm() {
		Board board = new Board(6, 5);
		Ghost ghost = new Ghost(board);

		GhostMovementAlgorithm algorithm = GhostMovementAlgorithmType.LINEAR
				.CreateInstance();
		ghost.setMovementAlgorithm(algorithm);

		board.SetObjectAt(board.BoardPosition(2, 2), ghost);

		/**
		 * <pre>
		 * +++++
		 * +   #
		 * + & +
		 * +   +
		 * +   +
		 * ++#++
		 * </pre>
		 */
		boolean[][] explored = { { true, true, true, true, true },
				{ true, true, true, true, false},
				{ true, true, true, true, true },
				{ true, true, true, true, true },
				{ true, true, true, true, true },
				{ true, true, false, true, true } };

		ghost.setExploredPositions(explored);

		BoardPosition position = algorithm.DetermineNextPosition(ghost);

		// assert that the position chosen is unexplored
		assertFalse(ghost.getExploredPositions()[position.getRow()][position
				.getColumn()]);

		// assert that the nearest linear position was chosen
		assertEquals(board.BoardPosition(5, 2), position);

		// now set up a tie between two positions (top/bottom left diagonals)
		/**
		 * <pre>
		 * ++++#
		 * +   +
		 * + & +
		 * +   +
		 * #   +
		 * ++#++
		 * </pre>
		 */
		explored[0][4] = false;
		explored[1][4] = true;
		explored[4][0] = false;

		ghost.setExploredPositions(explored);

		position = algorithm.DetermineNextPosition(ghost);

		// assert that the position chosen is unexplored
		assertFalse(ghost.getExploredPositions()[position.getRow()][position
				.getColumn()]);

		// assert that it's one of the two possibilities
		assertTrue(position.equals(board.BoardPosition(0, 4))
				|| position.equals(board.BoardPosition(4, 0)));
	}

	@Test
	public void testNoLinearUnexploredSpaces() {
		Board board = new Board(5, 5);
		Ghost ghost = new Ghost(board);

		GhostMovementAlgorithm algorithm = GhostMovementAlgorithmType.LINEAR
				.CreateInstance();
		ghost.setMovementAlgorithm(algorithm);

		board.SetObjectAt(board.BoardPosition(2, 2), ghost);

		/**
		 * <pre>
		 * +++++
		 * #   +
		 * + & +
		 * +   #
		 * +++++
		 * </pre>
		 */
		boolean[][] explored = { { true, true, true, true, true },
				{ false, true, true, true, true },
				{ true, true, true, true, true },
				{ true, true, true, true, false },
				{ true, true, true, true, true } };

		ghost.setExploredPositions(explored);

		BoardPosition position = algorithm.DetermineNextPosition(ghost);

		// assert that the position chosen is unexplored
		assertFalse(ghost.getExploredPositions()[position.getRow()][position
				.getColumn()]);

		// assert that it's one of the two possibilities
		assertTrue(position.equals(board.BoardPosition(1, 0))
				|| position.equals(board.BoardPosition(3, 4)));
	}

}
