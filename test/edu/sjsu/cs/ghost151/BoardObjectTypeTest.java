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
public class BoardObjectTypeTest {

	@Test
	public void testEnum() {
		BoardObjectType[] objectTypes = { BoardObjectType.Empty,
				BoardObjectType.Target, BoardObjectType.Wall,
				BoardObjectType.Ghost };

		assertArrayEquals(BoardObjectType.values(), objectTypes);

		assertEquals(BoardObjectType.Empty, BoardObjectType.valueOf("Empty"));
		assertEquals(BoardObjectType.Target, BoardObjectType.valueOf("Target"));
		assertEquals(BoardObjectType.Wall, BoardObjectType.valueOf("Wall"));
		assertEquals(BoardObjectType.Ghost, BoardObjectType.valueOf("Ghost"));
	}

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.BoardObjectType#toString()}.
	 */
	@Test
	public void testToString() {
		assertEquals(" ", BoardObjectType.Empty.toString());
		assertEquals("@", BoardObjectType.Target.toString());
		assertEquals("+", BoardObjectType.Wall.toString());
		assertEquals("&", BoardObjectType.Ghost.toString());
	}

}
