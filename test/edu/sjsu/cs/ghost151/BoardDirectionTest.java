package edu.sjsu.cs.ghost151;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardDirectionTest {
	@Test
	public void testEnumValues() {
		BoardDirection[] all = { BoardDirection.LEFTUP, BoardDirection.UP,
				BoardDirection.RIGHTUP, BoardDirection.LEFT, BoardDirection.STAYPUT,
				BoardDirection.RIGHT, BoardDirection.LEFTDOWN,
				BoardDirection.DOWN, BoardDirection.RIGHTDOWN};
		
		
		assertArrayEquals(all, BoardDirection.values());
		
		assertEquals(BoardDirection.LEFT, BoardDirection.valueOf("LEFT"));
	}
	
	@Test
	public void testCompassDirections() {
		BoardDirection[] compass = { BoardDirection.LEFTUP, BoardDirection.UP,
				BoardDirection.RIGHTUP, BoardDirection.LEFT,
				BoardDirection.RIGHT, BoardDirection.LEFTDOWN,
				BoardDirection.DOWN, BoardDirection.RIGHTDOWN };

		assertArrayEquals(compass, BoardDirection.CompassDirections());
	}

	@Test
	public void testPositionFrom() {
		Board board = new Board(3,3);
		BoardPosition origin = board.BoardPosition(1, 1);
		BoardPosition target = board.BoardPosition(1, 0);

		assertEquals(target, BoardDirection.LEFT.PositionFrom(origin));
	}

	@Test
	public void testOffsets() {
		assertEquals(BoardDirection.LEFTUP, BoardDirection.FromOffsets(-2, -2));
		assertEquals(-1, BoardDirection.LEFTUP.getColumnOffset());
		assertEquals(-1, BoardDirection.LEFTUP.getRowOffset());

		assertEquals(BoardDirection.UP, BoardDirection.FromOffsets(0, -2));
		assertEquals(0, BoardDirection.UP.getColumnOffset());
		assertEquals(-1, BoardDirection.UP.getRowOffset());

		assertEquals(BoardDirection.RIGHTUP, BoardDirection.FromOffsets(2, -2));
		assertEquals(1, BoardDirection.RIGHTUP.getColumnOffset());
		assertEquals(-1, BoardDirection.RIGHTUP.getRowOffset());

		assertEquals(BoardDirection.LEFT, BoardDirection.FromOffsets(-2, 0));
		assertEquals(-1, BoardDirection.LEFT.getColumnOffset());
		assertEquals(0, BoardDirection.LEFT.getRowOffset());

		assertEquals(BoardDirection.STAYPUT, BoardDirection.FromOffsets(0, 0));
		assertEquals(0, BoardDirection.STAYPUT.getColumnOffset());
		assertEquals(0, BoardDirection.STAYPUT.getRowOffset());

		assertEquals(BoardDirection.RIGHT, BoardDirection.FromOffsets(2, 0));
		assertEquals(1, BoardDirection.RIGHT.getColumnOffset());
		assertEquals(0, BoardDirection.RIGHT.getRowOffset());

		assertEquals(BoardDirection.LEFTDOWN, BoardDirection.FromOffsets(-2, 2));
		assertEquals(-1, BoardDirection.LEFTDOWN.getColumnOffset());
		assertEquals(1, BoardDirection.LEFTDOWN.getRowOffset());

		assertEquals(BoardDirection.DOWN, BoardDirection.FromOffsets(0, 2));
		assertEquals(0, BoardDirection.DOWN.getColumnOffset());
		assertEquals(1, BoardDirection.DOWN.getRowOffset());

		assertEquals(BoardDirection.RIGHTDOWN, BoardDirection.FromOffsets(2, 2));
		assertEquals(1, BoardDirection.RIGHTDOWN.getColumnOffset());
		assertEquals(1, BoardDirection.RIGHTDOWN.getRowOffset());
	}

	@Test
	public void testHorizontalComponent() {
		assertEquals(BoardDirection.LEFT,
				BoardDirection.LEFTUP.HorizontalComponent());

		assertEquals(BoardDirection.STAYPUT,
				BoardDirection.UP.HorizontalComponent());

		assertEquals(BoardDirection.RIGHT,
				BoardDirection.RIGHTUP.HorizontalComponent());

		assertEquals(BoardDirection.LEFT,
				BoardDirection.LEFT.HorizontalComponent());

		assertEquals(BoardDirection.STAYPUT,
				BoardDirection.STAYPUT.HorizontalComponent());

		assertEquals(BoardDirection.RIGHT,
				BoardDirection.RIGHT.HorizontalComponent());

		assertEquals(BoardDirection.LEFT,
				BoardDirection.LEFTDOWN.HorizontalComponent());

		assertEquals(BoardDirection.STAYPUT,
				BoardDirection.DOWN.HorizontalComponent());

		assertEquals(BoardDirection.RIGHT,
				BoardDirection.RIGHTDOWN.HorizontalComponent());
	}

	@Test
	public void testVerticalComponent() {
		assertEquals(BoardDirection.UP,
				BoardDirection.LEFTUP.VerticalComponent());

		assertEquals(BoardDirection.UP, BoardDirection.UP.VerticalComponent());

		assertEquals(BoardDirection.UP,
				BoardDirection.RIGHTUP.VerticalComponent());

		assertEquals(BoardDirection.STAYPUT,
				BoardDirection.LEFT.VerticalComponent());

		assertEquals(BoardDirection.STAYPUT,
				BoardDirection.STAYPUT.VerticalComponent());

		assertEquals(BoardDirection.STAYPUT,
				BoardDirection.RIGHT.VerticalComponent());

		assertEquals(BoardDirection.DOWN,
				BoardDirection.LEFTDOWN.VerticalComponent());

		assertEquals(BoardDirection.DOWN,
				BoardDirection.DOWN.VerticalComponent());

		assertEquals(BoardDirection.DOWN,
				BoardDirection.RIGHTDOWN.VerticalComponent());
	}
}
