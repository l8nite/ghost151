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
public class BoardPositionTest {

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.BoardPosition#getRow()}.
	 */
	@Test
	public void testGetRow() {
		BoardPosition position = new BoardPosition(10, 5);
		assertEquals(10, position.getRow());
	}

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.BoardPosition#setRow(int)}.
	 */
	@Test
	public void testSetRow() {
		BoardPosition position = new BoardPosition(1, 5);
		position.setRow(20);
		assertEquals(20, position.getRow());
	}

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.BoardPosition#getColumn()}.
	 */
	@Test
	public void testGetColumn() {
		BoardPosition position = new BoardPosition(10, 15);
		assertEquals(15, position.getColumn());
	}

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.BoardPosition#setColumn(int)}
	 * .
	 */
	@Test
	public void testSetColumn() {
		BoardPosition position = new BoardPosition(10, 15);
		position.setColumn(4);
		assertEquals(4, position.getColumn());
	}

}
