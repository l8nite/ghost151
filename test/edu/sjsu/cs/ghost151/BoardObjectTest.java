/**
 * 
 */
package edu.sjsu.cs.ghost151;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author      Alben Cheung
 * @author      MD Ashfaqul Islam
 * @author      Shaun Guth
 * @author      Jerry Phul
 */
public class BoardObjectTest {

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.BoardObject#getPosition()}.
	 */
	@Test
	public void testGetPosition() {
		BoardObjectType typeTest = BoardObjectType.Ghost;
		BoardObject positionTest = new BoardObject(typeTest);
		BoardPosition position = new BoardPosition(10,2);
		positionTest.setPosition(position);
		assertNotNull(positionTest.getPosition());
	}

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.BoardObject#setPosition(edu.sjsu.cs.ghost151.BoardPosition)}.
	 */
	@Test
	public void testSetPosition() {
		BoardObjectType typeTest = BoardObjectType.Ghost;
		BoardObject positionTest = new BoardObject(typeTest);
		BoardPosition position = new BoardPosition(10,2);
		positionTest.setPosition(position);
		assertEquals(position,positionTest.getPosition());
	}

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.BoardObject#getType()}.
	 */
	@Test
	public void testGetType() {
		BoardObjectType typeTest = BoardObjectType.Ghost;
		BoardObject objectTest = new BoardObject(typeTest);
		assertNotNull(objectTest.getType());
	}

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.BoardObject#setType(edu.sjsu.cs.ghost151.BoardObjectType)}.
	 */
	@Test
	public void testSetType() {
		BoardObjectType typeTest = BoardObjectType.Ghost;
		BoardObject objectTest = new BoardObject(typeTest);
		assertEquals(typeTest,objectTest.getType());
	}

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.BoardObject#getBoard()}.
	 */
	@Test
	public void testGetBoard() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.BoardObject#setBoard(edu.sjsu.cs.ghost151.Board)}.
	 */
	@Test
	public void testSetBoard() {
		fail("Not yet implemented");
	}

}
