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

	private Board board;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		board = Board.INSTANCE;
	}

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.Board#Initialize()}.
	 */
	@Test
	public void testInitialize() {
		board.Initialize();
		BoardObject[][] grid = board.getGrid();

		// test that the walls are all of type wall, and the rest are empty
		for (int row = 0; row < Board.ROWS; ++row) {
			for (int column = 0; column < Board.COLUMNS; ++column) {
				BoardObject object = grid[row][column];

				if (row == 0 || row == Board.ROWS - 1 || column == 0
						|| column == Board.COLUMNS - 1) {
					assertEquals(BoardObjectType.Wall, object.getType());
				} else {
					assertEquals(BoardObjectType.Empty, object.getType());
				}
			}
		}
	}

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.Board#toString()}.
	 */
	@Test
	public void testToString() {
		board.Initialize();
		String expected = "++++++++++++++++++++\n" 
						+ "+                  +\n"
						+ "+                  +\n" 
						+ "+                  +\n"
						+ "+                  +\n" 
						+ "+                  +\n"
						+ "+                  +\n" 
						+ "+                  +\n"
						+ "+                  +\n" 
						+ "++++++++++++++++++++\n";
		assertEquals(expected, board.toString());
	}

	/**
	 * Test method for
	 * {@link edu.sjsu.cs.ghost151.Board#SetObjectAt(edu.sjsu.cs.ghost151.BoardPosition, edu.sjsu.cs.ghost151.BoardObject)}
	 * .
	 */
	@Test
	public void testSetObjectAt() {
		board.Initialize();

		BoardObject boardObject = new BoardObject(BoardObjectType.Ghost);
		BoardPosition boardPosition = new BoardPosition(5, 5);

		board.SetObjectAt(boardPosition, boardObject);

		// test that the board has the right object
		assertEquals(boardObject, board.GetObjectAt(boardPosition));

		// test that the position on the object was updated
		assertEquals(boardPosition, boardObject.getPosition());
	}

	/**
	 * Test method for
	 * {@link edu.sjsu.cs.ghost151.Board#GetObjectAt(edu.sjsu.cs.ghost151.BoardPosition)}
	 * .
	 */
	@Test
	public void testGetObjectAt() {
		board.Initialize();

		BoardObject boardObject = board.getGrid()[0][0];
		BoardPosition boardPosition = new BoardPosition(0, 0);

		assertEquals(boardObject, board.GetObjectAt(boardPosition));
	}

	/**
	 * Test method for
	 * {@link edu.sjsu.cs.ghost151.Board#GetSurroundings(edu.sjsu.cs.ghost151.BoardObject)}
	 * .
	 */
	@Test
	public void testGetSurroundings() {
		board.Initialize();
		
		// surrounding position 1,1 with ghosts and walls, then check that's what we get back
		board.SetObjectAt(new BoardPosition(1, 2), new BoardObject(BoardObjectType.Ghost));
		board.SetObjectAt(new BoardPosition(2, 1), new BoardObject(BoardObjectType.Ghost));
		board.SetObjectAt(new BoardPosition(2, 2), new BoardObject(BoardObjectType.Ghost));
		
		BoardObject target = new BoardObject(BoardObjectType.Target);
		BoardPosition targetPosition = new BoardPosition(1, 1);
		board.SetObjectAt(targetPosition, target);
		
		BoardObject[] surroundings = board.GetSurroundings(target);
		
		assertEquals(8, surroundings.length);
		
		for (int i = 0; i < 4; ++i)
		{
			assertEquals(BoardObjectType.Wall, surroundings[i].getType());
		}
		
		assertEquals(BoardObjectType.Ghost, surroundings[4].getType());
		assertEquals(BoardObjectType.Wall, surroundings[5].getType());
		assertEquals(BoardObjectType.Ghost, surroundings[6].getType());
		assertEquals(BoardObjectType.Ghost, surroundings[7].getType());
	}
}
