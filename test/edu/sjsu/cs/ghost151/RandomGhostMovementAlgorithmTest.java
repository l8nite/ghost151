/**
 * 
 */
package edu.sjsu.cs.ghost151;

import static org.junit.Assert.*;

import org.junit.Test;

public class RandomGhostMovementAlgorithmTest {

	@Test
	public void testRandomGhostMovementAlgorithm() {
		Board board = new Board(5, 5);
		Ghost ghost = new Ghost(board);

		GhostMovementAlgorithm algorithm = GhostMovementAlgorithmType.RANDOM
				.CreateInstance();
		ghost.setMovementAlgorithm(algorithm);

		board.SetObjectAt(board.BoardPosition(2, 2), ghost);

		ghost.Scan();

		BoardPosition position = algorithm.DetermineNextPosition(ghost);

		// assert that the position chosen is unexplored
		assertFalse(ghost.getExploredPositions()[position.getRow()][position
				.getColumn()]);
	}
	
	@Test
	public void testNoUnexploredSpaces() {
		Board board = new Board(3, 3);
		Ghost ghost = new Ghost(board);

		GhostMovementAlgorithm algorithm = GhostMovementAlgorithmType.RANDOM
				.CreateInstance();
		ghost.setMovementAlgorithm(algorithm);

		board.SetObjectAt(board.BoardPosition(1, 1), ghost);

		ghost.Scan();

		BoardPosition position = algorithm.DetermineNextPosition(ghost);

		assertEquals(ghost.getPosition(), position);
	}

}
