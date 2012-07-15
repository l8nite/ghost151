package edu.sjsu.cs.ghost151;

import static org.junit.Assert.*;

import org.junit.Test;

public class GhostMovementAlgorithmTypeTest {

	@Test
	public void testEnum() {
		GhostMovementAlgorithmType[] objectTypes = { 
			GhostMovementAlgorithmType.RANDOM,
			GhostMovementAlgorithmType.LINEAR,
			GhostMovementAlgorithmType.NEAREST,
		};

		assertArrayEquals(GhostMovementAlgorithmType.values(), objectTypes);

		assertEquals(GhostMovementAlgorithmType.RANDOM, GhostMovementAlgorithmType.valueOf("RANDOM"));
	}
	
	@Test
	public void testCreateInstance() {
		GhostMovementAlgorithm random = GhostMovementAlgorithmType.RANDOM.CreateInstance();
		assertTrue(random instanceof RandomGhostMovementAlgorithm);
		
		GhostMovementAlgorithm linear = GhostMovementAlgorithmType.LINEAR.CreateInstance();
		assertTrue(linear instanceof LinearGhostMovementAlgorithm);
		
		GhostMovementAlgorithm nearest = GhostMovementAlgorithmType.NEAREST.CreateInstance();
		assertTrue(nearest instanceof NearestGhostMovementAlgorithm);
	}
}
