/**
 * 
 */
package edu.sjsu.cs.ghost151;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Alben Cheung
 * @author MD Ashfaqul Islam
 * @author Shaun Guth
 * @author Jerry Phul
 */
public class BoardPositionTest {

	private Board board;

	@Before
	public void setUp() {
		board = new Board(3, 3);
	}

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.BoardPosition#setRow(int)}.
	 */
	@Test
	public void testGetSetRow() {
		BoardPosition position = board.BoardPosition(2, 1);
		position.setRow(1);
		assertEquals(1, position.getRow());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetRowLowerBound() {
		(board.BoardPosition(0, 0)).setRow(-1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetRowUpperBound() {
		(board.BoardPosition(0, 0)).setRow(board.ROWS);
	}

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.BoardPosition#setColumn(int)}
	 */
	@Test
	public void testGetSetColumn() {
		BoardPosition position = board.BoardPosition(1, 2);
		position.setColumn(1);
		assertEquals(1, position.getColumn());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetColumnLowerBound() {
		(board.BoardPosition(0, 0)).setColumn(-1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetColumnUpperBound() {
		(board.BoardPosition(0, 0)).setColumn(board.COLUMNS);
	}

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.BoardPosition#toString()}
	 */
	@Test
	public void testToString() {
		BoardPosition position = board.BoardPosition(1, 2);
		assertEquals("(1, 2)", position.toString());
	}

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.BoardPosition#equals(Object)}
	 */
	@Test
	public void testEquals() {
		BoardPosition a = board.BoardPosition(0, 0);
		BoardPosition b = board.BoardPosition(0, 0);
		BoardPosition c = board.BoardPosition(1, 0);
		BoardPosition d = board.BoardPosition(1, 1);

		assertTrue(a.equals(a)); // same instance
		assertTrue(a.equals(b)); // same coordinates
		assertFalse(a.equals(c)); // different column
		assertFalse(c.equals(d)); // different row

		assertFalse(a.equals(new Object()));
	}

	/**
	 * Test method for
	 * {@link edu.sjsu.cs.ghost151.BoardPosition#DirectionFrom(BoardPosition)}
	 */
	@Test
	public void testDirectionFrom() {
		BoardPosition center = board.BoardPosition(1, 1);
		assertEquals(BoardDirection.RIGHTDOWN,
				center.DirectionFrom(board.BoardPosition(0, 0)));
		assertEquals(BoardDirection.DOWN,
				center.DirectionFrom(board.BoardPosition(0, 1)));
		assertEquals(BoardDirection.LEFTDOWN,
				center.DirectionFrom(board.BoardPosition(0, 2)));
		assertEquals(BoardDirection.RIGHT,
				center.DirectionFrom(board.BoardPosition(1, 0)));
		assertEquals(BoardDirection.STAYPUT,
				center.DirectionFrom(board.BoardPosition(1, 1)));
		assertEquals(BoardDirection.LEFT,
				center.DirectionFrom(board.BoardPosition(1, 2)));
		assertEquals(BoardDirection.RIGHTUP,
				center.DirectionFrom(board.BoardPosition(2, 0)));
		assertEquals(BoardDirection.UP,
				center.DirectionFrom(board.BoardPosition(2, 1)));
		assertEquals(BoardDirection.LEFTUP,
				center.DirectionFrom(board.BoardPosition(2, 2)));
	}
}
