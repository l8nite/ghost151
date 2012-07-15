package edu.sjsu.cs.ghost151;

import static org.junit.Assert.*;

import org.junit.Test;

public class GhostTest {

	@Test
	public void testSetGetMovementAlgorithm() {
		GhostMovementAlgorithm algorithm = GhostMovementAlgorithmType.LINEAR
				.CreateInstance();

		Ghost ghost = new Ghost(new Board());

		ghost.setMovementAlgorithm(algorithm);
		assertEquals(algorithm, ghost.getMovementAlgorithm());
	}

	@Test
	public void testMoveDirection() {
		Board board = new Board(4, 4);
		Ghost ghost = new Ghost(board);
		board.SetObjectAt(board.BoardPosition(1, 1), ghost);
		board.SetObjectAt(board.BoardPosition(1, 2), new BoardObject(
				BoardObjectType.Target));

		assertFalse(ghost.AbleToMoveDirection(BoardDirection.UP));
		assertTrue(ghost.AbleToMoveDirection(BoardDirection.RIGHT));
		assertTrue(ghost.AbleToMoveDirection(BoardDirection.DOWN));
	}

	@Test
	public void testMoveTo() {
		Board board = new Board(4, 4);
		Ghost ghost = new Ghost(board);

		BoardPosition position = board.BoardPosition(1, 1);

		board.SetObjectAt(position, ghost);

		int movements = Game.INSTANCE.getNumberOfMovements();

		// test moving to a null position
		ghost.MoveTo(null);

		// make sure the movement counter hasn't incremented
		assertEquals(movements, Game.INSTANCE.getNumberOfMovements());
		// and that the ghost hasn't moved
		assertEquals(position, ghost.getPosition());

		// test moving with an invalid BoardPosition (wrong board)
		ghost.MoveTo(new BoardPosition(0, 0, new Board()));

		// make sure the movement counter hasn't incremented
		assertEquals(movements, Game.INSTANCE.getNumberOfMovements());
		// and that the ghost hasn't moved
		assertEquals(position, ghost.getPosition());

		// test moving to an invalid move target (wall)
		ghost.MoveTo(board.BoardPosition(0, 0));

		// make sure the movement counter hasn't incremented
		assertEquals(movements, Game.INSTANCE.getNumberOfMovements());
		// and that the ghost hasn't moved
		assertEquals(position, ghost.getPosition());

		// test that moving to a position with a target acquires the target
		BoardPosition oldPosition = ghost.getPosition();
		BoardPosition targetPosition = board.BoardPosition(1, 2);
		board.SetObjectAt(targetPosition, new BoardObject(
				BoardObjectType.Target));
		ghost.MoveTo(targetPosition);

		// make sure the movement counter incremented
		assertEquals(movements + 1, Game.INSTANCE.getNumberOfMovements());
		// and that the ghost moved to the new position
		assertEquals(targetPosition, ghost.getPosition());
		// and that the position the ghost moved from is now empty
		assertEquals(BoardObjectType.Empty, board.GetObjectAt(oldPosition)
				.getType());

	}

	@Test
	public void testVerticalEdgeNavigation() {
		/**
		 * <pre>
		 * +++#
		 * +  +
		 * + &+
		 * ++++
		 * </pre>
		 */
		boolean[][] explored = { { true, true, true, false },
				{ true, true, true, true }, { true, true, true, true },
				{ true, true, true, true }, };

		Board board = new Board(4, 4);
		Ghost ghost = new Ghost(board);
		ghost.setPosition(board.BoardPosition(2, 2));
		ghost.setExploredPositions(explored);
		ghost.setMovementAlgorithm(GhostMovementAlgorithmType.RANDOM
				.CreateInstance());

		ghost.Move();

		assertEquals(board.BoardPosition(1, 2), ghost.getPosition());
	}

	@Test
	public void testHorizontalEdgeNavigation() {
		/**
		 * <pre>
		 * #+++
		 * + &+
		 * +  +
		 * ++++
		 * </pre>
		 */
		boolean[][] explored = { { false, true, true, true },
				{ true, true, true, true }, { true, true, true, true },
				{ true, true, true, true }, };

		Board board = new Board(4, 4);
		Ghost ghost = new Ghost(board);
		ghost.setPosition(board.BoardPosition(1, 2));
		ghost.setExploredPositions(explored);
		ghost.setMovementAlgorithm(GhostMovementAlgorithmType.RANDOM
				.CreateInstance());

		ghost.Move();

		assertEquals(board.BoardPosition(1, 1), ghost.getPosition());
	}

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.Ghost#IsTargetAcquired()} .
	 */
	@Test
	public void testGhostTargetAcquisition() {
		Board board = new Board(5, 5);
		Ghost ghost = new Ghost(board);
		assertFalse(ghost.IsTargetAcquired());

		board.Initialize();

		// put the ghost and target next to each other
		board.SetObjectAt(board.BoardPosition(0, 0), ghost);
		board.SetObjectAt(board.BoardPosition(1, 1), new BoardObject(
				BoardObjectType.Target));

		// have the ghost scan, move, and then see if it acquires the target
		ghost.Scan();
		ghost.Move();
		assertTrue(ghost.IsTargetAcquired());
	}

	/**
	 * Test method for
	 * {@link edu.sjsu.cs.ghost151.Ghost#CommunicateWith(edu.sjsu.cs.ghost151.Ghost)}
	 * .
	 */
	@Test
	public void testGhostCommunication() {
		Board board = new Board();
		board.Initialize();

		// put two ghosts on the board near one another and then have them scan
		// to see if they communicate
		Ghost ghost1 = new Ghost(board);
		board.SetObjectAt(board.BoardPosition(1, 1), ghost1);

		Ghost ghost2 = new Ghost(board);
		board.SetObjectAt(board.BoardPosition(2, 2), ghost2);

		// have them scan the area, they will see each other and communicate
		ghost1.Scan();
		ghost2.Scan();

		ghost1.CommunicateWith(ghost2);
		ghost2.CommunicateWith(ghost1);

		// see if ghost1 knows about something that only ghost2 would have seen,
		// like
		// what's at position 3, 3
		boolean[][] ghost1ExploredPositions = ghost1.getExploredPositions();

		assertTrue(ghost1ExploredPositions[3][3]);

		// also see if ghost2 knows something that only ghost1 can see, like
		// what's at position 0, 0
		boolean[][] ghost2ExploredPositions = ghost2.getExploredPositions();

		assertTrue(ghost2ExploredPositions[0][0]);
	}
}