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
public class GameTest {

	/**
	 * Test method for
	 * {@link edu.sjsu.cs.ghost151.Game#main(java.lang.String[])}.
	 */
	@Test
	public void testMain() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link edu.sjsu.cs.ghost151.Game#getNumberOfCommunications()}.
	 */
	@Test
	public void testGetNumberOfCommunications() {
		Game test1 = Game.INSTANCE;
		test1.setNumberOfCommunications(40);
		assertNotNull(test1.getNumberOfCommunications());
	}

	/**
	 * Test method for
	 * {@link edu.sjsu.cs.ghost151.Game#setNumberOfCommunications(int)}.
	 */
	@Test
	public void testSetNumberOfCommunications() {
		Game test2 = Game.INSTANCE;
		test2.setNumberOfCommunications(40);
		assertEquals(40, test2.getNumberOfCommunications());
	}

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.Game#getNumberOfGhosts()}.
	 */
	@Test
	public void testGetNumberOfGhosts() {
		Game test3 = Game.INSTANCE;
		int numOfGhosts = 6;
		test3.setNumberOfGhosts(numOfGhosts);
		assertNotNull(test3.getNumberOfGhosts());
	}

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.Game#setNumberOfGhosts(int)}.
	 */
	@Test
	public void testSetNumberOfGhosts() {
		Game test4 = Game.INSTANCE;
		int numOfGhosts = 6;
		test4.setNumberOfGhosts(numOfGhosts);
		assertEquals(numOfGhosts, test4.getNumberOfGhosts());
	}

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.Game#getNumberOfMovements()}.
	 */
	@Test
	public void testGetNumberOfMovements() {
		Game test5 = Game.INSTANCE;
		test5.setNumberOfMovements(20);
		assertNotNull(test5.getNumberOfMovements());
	}

	/**
	 * Test method for
	 * {@link edu.sjsu.cs.ghost151.Game#setNumberOfMovements(int)}.
	 */
	@Test
	public void testSetNumberOfMovements() {
		Game test6 = Game.INSTANCE;
		test6.setNumberOfMovements(20);
		assertEquals(20, test6.getNumberOfMovements());
	}

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.Game#getGhosts()}.
	 */
	@Test
	public void testGetGhosts() {
		Game test7 = Game.INSTANCE;
		Ghost ghostsTest[];
		int numOfGhosts = 4;

		ghostsTest = new Ghost[numOfGhosts];
		test7.setGhosts(ghostsTest);
		assertNotNull(test7.getGhosts());
	}

	/**
	 * Test method for
	 * {@link edu.sjsu.cs.ghost151.Game#setGhosts(edu.sjsu.cs.ghost151.Ghost[])}
	 * .
	 */
	@Test
	public void testSetGhosts() {
		Game test8 = Game.INSTANCE;
		Ghost ghostsTest[];
		int numOfGhosts = 4;

		ghostsTest = new Ghost[numOfGhosts];
		test8.setGhosts(ghostsTest);
		assertArrayEquals(test8.getGhosts(), ghostsTest);
	}

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.Game#getBoard()}.
	 */
	@Test
	public void testGetBoard() {
		Board boardTest = Board.INSTANCE;
		Game test9 = Game.INSTANCE;

		test9.setBoard(boardTest);
		assertNotNull(test9.getBoard());
	}

	/**
	 * Test method for
	 * {@link edu.sjsu.cs.ghost151.Game#setBoard(edu.sjsu.cs.ghost151.Board)}.
	 */
	@Test
	public void testSetBoard() {
		Board boardTest = Board.INSTANCE;
		Game test10 = Game.INSTANCE;

		test10.setBoard(boardTest);
		Board settedBoard = test10.getBoard();
		assertEquals(settedBoard, boardTest);
	}

}
