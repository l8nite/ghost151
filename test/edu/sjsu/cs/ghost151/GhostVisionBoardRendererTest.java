package edu.sjsu.cs.ghost151;

import static org.junit.Assert.*;

import org.junit.Test;

public class GhostVisionBoardRendererTest {

	@Test
	public void test() {
		Board board = new Board(5,5);
		Ghost ghost1 = new Ghost(board);
		Ghost ghost2 = new Ghost(board);
		
		board.setRenderer(new GhostVisionBoardRenderer());
		
		board.SetObjectAt(board.BoardPosition(1,1), ghost1);
		board.SetObjectAt(board.BoardPosition(3,3), ghost2);
		board.SetObjectAt(board.BoardPosition(4,0), new BoardObject(BoardObjectType.Target));
		String expected = 
		"+++##\n" +
		"+& ##\n" +
		"+   +\n" +
		"## &+\n" +
		"@#+++\n";
		
		assertEquals(expected, board.toString());
	}

}
