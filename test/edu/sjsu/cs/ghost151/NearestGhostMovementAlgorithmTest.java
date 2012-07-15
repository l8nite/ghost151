/**
 * 
 */
package edu.sjsu.cs.ghost151;

import static org.junit.Assert.*;

import org.junit.Test;

public class NearestGhostMovementAlgorithmTest {

	@Test
	public void testNearestGhostMovementAlgorithm() {
		Board board = new Board(5, 5);
		Ghost ghost = new Ghost(board);

		GhostMovementAlgorithm algorithm = GhostMovementAlgorithmType.NEAREST
				.CreateInstance();
		ghost.setMovementAlgorithm(algorithm);

		board.SetObjectAt(board.BoardPosition(2, 3), ghost);

		/**
		 * <pre>
		 * ++#++
		 * +   +
		 * +  &+
		 * +   +
		 * #++++
		 * </pre>
		 */
		boolean[][] explored = { { true, true, false, true, true },
				{ true, true, true, true, true },
				{ true, true, true, true, true },
				{ true, true, true, true, true },
				{ false, true, true, true, true } };

		ghost.setExploredPositions(explored);

		BoardPosition position = algorithm.DetermineNextPosition(ghost);

		// assert that the position chosen is unexplored
		assertFalse(ghost.getExploredPositions()[position.getRow()][position
				.getColumn()]);

		// assert that the nearest position was chosen
		assertEquals(board.BoardPosition(0, 2), position);

		// now set up a tie between two positions (top/bottom left corners)
		explored[0][0] = false;
		explored[0][2] = true;

		ghost.setExploredPositions(explored);

		position = algorithm.DetermineNextPosition(ghost);

		// assert that the position chosen is unexplored
		assertFalse(ghost.getExploredPositions()[position.getRow()][position
				.getColumn()]);

		// assert that it's one of the two possibilities
		assertTrue(position.equals(board.BoardPosition(0, 0))
				|| position.equals(board.BoardPosition(4, 0)));
	}

	@Test
	public void testNoUnexploredSpaces() {
		Board board = new Board(3, 3);
		Ghost ghost = new Ghost(board);

		GhostMovementAlgorithm algorithm = GhostMovementAlgorithmType.NEAREST
				.CreateInstance();
		ghost.setMovementAlgorithm(algorithm);
		
		board.SetObjectAt(board.BoardPosition(1, 1), ghost);

		ghost.Scan();
		
		BoardPosition position = algorithm.DetermineNextPosition(ghost);

		assertEquals(ghost.getPosition(), position);
	}

}
