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
public class BoardObjectTest {

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.BoardObject#getPosition()}
	 * and
	 * {@link edu.sjsu.cs.ghost151.BoardObject#setPosition(edu.sjsu.cs.ghost151.BoardPosition)}
	 */
	@Test
	public void testGetSetPosition() {
		BoardObject object = new BoardObject(BoardObjectType.Ghost);
		assertNull(object.getPosition());

		BoardPosition position = new BoardPosition(10, 2);
		object.setPosition(position);
		assertEquals(position, object.getPosition());

	}

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.BoardObject#getType()} and
	 * {@link edu.sjsu.cs.ghost151.BoardObject#setType(edu.sjsu.cs.ghost151.BoardObjectType)}
	 */
	@Test
	public void testGetSetType() {
		BoardObject object = new BoardObject(BoardObjectType.Ghost);

		assertEquals(BoardObjectType.Ghost, object.getType());
		// *poof* you're a wall.
		object.setType(BoardObjectType.Wall);
		assertEquals(BoardObjectType.Wall, object.getType());
	}

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.BoardObject#toString()}.
	 * Tests that rendering a BoardObject is the same as rendering its
	 * corresponding BoardObjectType
	 */
	@Test
	public void testToString() {
		BoardObjectType ghostType = BoardObjectType.Ghost;
		BoardObject object = new BoardObject(ghostType);

		String expected = ghostType.toString();
		String actual = object.toString();
		assertEquals(expected, actual);
	}

}
