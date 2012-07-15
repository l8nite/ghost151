package edu.sjsu.cs.ghost151;

import static org.junit.Assert.*;

import org.junit.Test;

public class ScoredBoardPositionTest {

	@Test
	public void testScoredBoardPosition() {
		Board board = new Board(3, 3);
		ScoredBoardPosition a = new ScoredBoardPosition(0, 0, board);

		assertEquals(-1, a.getScore());

		a.setScore(1);

		assertEquals(1, a.getScore());

		a = new ScoredBoardPosition(0, 0, board, 2);

		assertEquals(2, a.getScore());
	}

}
