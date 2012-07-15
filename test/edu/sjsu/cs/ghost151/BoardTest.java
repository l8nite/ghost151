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
public class BoardTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		new Board(10, 20);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testInvalidBoardSize() {
		new Board(1, 1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testInvalidBoardRows() {
		new Board(1, 5);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testInvalidBoardColumns() {
		new Board(5, 1);
	}
	
	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.Board#Initialize()}.
	 */
	@Test
	public void testInitialize() {
		Board board = new Board();
		BoardObject[][] grid = board.getGrid();

		// test that the walls are all of type wall, and the rest are empty
		for (int row = 0; row < board.ROWS; ++row) {
			for (int column = 0; column < board.COLUMNS; ++column) {
				BoardObject object = grid[row][column];

				if (row == 0 || row == board.ROWS - 1 || column == 0
						|| column == board.COLUMNS - 1) {
					assertEquals(BoardObjectType.Wall, object.getType());
				} else {
					assertEquals(BoardObjectType.Empty, object.getType());
				}
			}
		}
	}
	
	@Test
	public void testGetSetRenderer() {
		Board board = new Board();
		BoardRenderer r = new InvisibleBoardRenderer();
		board.setRenderer(r);
		assertEquals(r, board.getRenderer());
	}

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.Board#toString()}.
	 */
	@Test
	public void testToString() {
		Board board = new Board();
		String expected = "++++++++++++++++++++\n" + "+                  +\n"
				+ "+                  +\n" + "+                  +\n"
				+ "+                  +\n" + "+                  +\n"
				+ "+                  +\n" + "+                  +\n"
				+ "+                  +\n" + "++++++++++++++++++++\n";
		assertEquals(expected, board.toString());
	}

	/**
	 * Test method for
	 * {@link edu.sjsu.cs.ghost151.Board#SetObjectAt(edu.sjsu.cs.ghost151.BoardPosition, edu.sjsu.cs.ghost151.BoardObject)}
	 * .
	 */
	@Test
	public void testSetObjectAt() {
		Board board = new Board();

		BoardObject boardObject = new BoardObject(BoardObjectType.Ghost);
		BoardPosition boardPosition = board.BoardPosition(5, 5);

		board.SetObjectAt(boardPosition, boardObject);

		// test that the board has the right object
		assertEquals(boardObject, board.GetObjectAt(boardPosition));

		// test that the position on the object was updated
		assertEquals(boardPosition, boardObject.getPosition());

		// try setting an object using a position not from that board
		board.SetObjectAt(new BoardPosition(0, 0, new Board()), boardObject);

		// verify that 0, 0 doesn't have the new object
		assertNotSame(boardObject, board.GetObjectAt(board.BoardPosition(0, 0)));
	}

	/**
	 * Test method for
	 * {@link edu.sjsu.cs.ghost151.Board#GetObjectAt(edu.sjsu.cs.ghost151.BoardPosition)}
	 * .
	 */
	@Test
	public void testGetObjectAt() {
		Board board = new Board();

		BoardObject boardObject = board.getGrid()[0][0];
		BoardPosition boardPosition = board.BoardPosition(0, 0);

		assertEquals(boardObject, board.GetObjectAt(boardPosition));

		assertNull(board.GetObjectAt(new BoardPosition(0, 0, new Board())));
	}

	@Test
	public void testIsValidMoveTarget() {
		Board board = new Board();

		BoardObject ghost = new BoardObject(BoardObjectType.Ghost);
		BoardPosition ghostPosition = board.BoardPosition(1, 2);
		board.SetObjectAt(ghostPosition, ghost);

		assertFalse(board.IsValidMoveTarget(board.BoardPosition(0, 0)));
		assertFalse(board.IsValidMoveTarget(ghostPosition));
		assertTrue(board.IsValidMoveTarget(board.BoardPosition(1, 1)));

		assertFalse(board
				.IsValidMoveTarget(new BoardPosition(1, 1, new Board())));

	}
}
