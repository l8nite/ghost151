/**
 * 
 */
package edu.sjsu.cs.ghost151;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Alben Cheung
 * @author MD Ashfaqul Islam
 * @author Shaun Guth
 * @author Jerry Phul
 */
public class GhostTest {

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.Ghost#isTargetAcquired()} .
	 */
	@Test
	public void testGhostTargetAcquisition() {
		Ghost ghost = new Ghost();
		assertFalse(ghost.IsTargetAcquired());

		Board board = Board.INSTANCE;
		board.Initialize();

		// put the ghost and target next to each other
		board.SetObjectAt(new BoardPosition(1, 1), ghost);
		board.SetObjectAt(new BoardPosition(1, 2), new BoardObject(
				BoardObjectType.Target));

		// have the ghost scan and see if it acquires the target
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
		Board board = Board.INSTANCE;
		board.Initialize();

		// put two ghosts on the board near one another and then have them scan
		// to see if they communicate
		Ghost ghost1 = new Ghost();
		board.SetObjectAt(new BoardPosition(1, 1), ghost1);

		Ghost ghost2 = new Ghost();
		board.SetObjectAt(new BoardPosition(2, 2), ghost2);

		// have them scan the area, they will see each other and communicate
		ghost1.Scan();
		ghost2.Scan();
		
		ghost1.CommunicateWith(ghost2);
		ghost2.CommunicateWith(ghost1);

		// see if ghost1 knows about something that only ghost2 would have seen,
		// like
		// what's at position 3, 3
		boolean[][] ghost1ExploredPositions = ghost1
				.getExploredPositions();

		assertTrue(ghost1ExploredPositions[3][3]);

		// also see if ghost2 knows something that only ghost1 can see, like
		// what's at position 0, 0
		boolean[][] ghost2ExploredPositions = ghost2
				.getExploredPositions();

		assertTrue(ghost2ExploredPositions[0][0]);
	}

}
