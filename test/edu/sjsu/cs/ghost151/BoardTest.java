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
public class BoardTest {

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.Board#getGrid()}.
	 */
	@Test
	public void testGetGrid() {
		Board newBoard = Board.INSTANCE;
		BoardObject grid[][] = new BoardObject[10][10];
		newBoard.setGrid(grid);
		assertNotNull(newBoard.getGrid());
	}

	/**
	 * Test method for
	 * {@link edu.sjsu.cs.ghost151.Board#setGrid(edu.sjsu.cs.ghost151.BoardObject[][])}
	 * .
	 */
	@Test
	public void testSetGrid() {
		Board newBoard = Board.INSTANCE;
		BoardObject grid[][] = new BoardObject[5][10];
		newBoard.setGrid(grid);
		assertArrayEquals(grid, newBoard.getGrid());
	}

}
