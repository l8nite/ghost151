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
public class GhostTest {

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.Ghost#getExploredPositions()}
	 * .
	 */
	@Test
	public void testGetExploredPositions() {
		BoardObjectType[][] explored = new BoardObjectType[20][10];
		Ghost testGhost = new Ghost();
		testGhost.setExploredPositions(explored);
		assertNotNull(testGhost.getExploredPositions());
	}

	/**
	 * Test method for
	 * {@link edu.sjsu.cs.ghost151.Ghost#setExploredPositions(edu.sjsu.cs.ghost151.BoardPosition[])}
	 * .
	 */
	@Test
	public void testSetExploredPositions() {
		BoardObjectType[][] explored = new BoardObjectType[20][10];
		Ghost testGhost = new Ghost();
		testGhost.setExploredPositions(explored);
		assertArrayEquals(explored, testGhost.getExploredPositions());
	}

	@Test
	public void isTargetAcquiredTest(){
		Ghost targetTest = new Ghost();
		assertFalse(targetTest.IsTargetAcquired());
	}
}
