/**
 * 
 */
package edu.sjsu.cs.ghost151;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author shaung
 *
 */
public class BoardPositionTest {

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.BoardPosition#getRow()}.
	 */
	@Test
	public void testGetRow() {
		BoardPosition test = new BoardPosition(10, 5);
		assertEquals(10, test.getRow());
	}

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.BoardPosition#setRow(int)}.
	 */
	@Test
	public void testSetRow() {
		BoardPosition test = new BoardPosition(1, 5);
		test.setRow(20);
		assertEquals(20, test.getRow());
	}

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.BoardPosition#getColumn()}.
	 */
	@Test
	public void testGetColumn() {
		BoardPosition test = new BoardPosition(10, 15);
		assertEquals(15, test.getColumn());
	}

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.BoardPosition#setColumn(int)}.
	 */
	@Test
	public void testSetColumn() {
		BoardPosition test = new BoardPosition(10, 15);
		test.setColumn(4);
		assertEquals(4, test.getColumn());
	}

}
