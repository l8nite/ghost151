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
public class BoardObjectTypeTest {

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.BoardObjectType#getRepresentation()}.
	 */
	@Test
	public void testGetRepresentation() {
		assertEquals("@",BoardObjectType.Target.toString());
		assertEquals(" ",BoardObjectType.Empty.toString());
		assertEquals("&",BoardObjectType.Ghost.toString());
		assertEquals("+",BoardObjectType.Wall.toString());
	}

}
