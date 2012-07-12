package edu.sjsu.cs.ghost151;

/**
 * The <b>GhostMovementAlgorithmType</b> is an enumeration factory for creating
 * appropriate instances of the GhostMovementAlgorithm interface.
 * 
 * There are three algorithms defined:
 * <ul>
 * <li><b>RANDOM</b>: The ghost chooses a random unexplored position and moves
 * towards it</li>
 * <li><b>LINEAR</b>: The ghost scans linearly outward in all 8 directions from
 * its current position and moves towards the nearest unexplored space</li>
 * <li><b>NEAREST</b>: The ghost moves towards the nearest unexplored space</li>
 * </ul>
 */
public enum GhostMovementAlgorithmType {
	RANDOM, LINEAR, NEAREST;

	public GhostMovementAlgorithm CreateInstance() {
		switch (this) {
		case RANDOM:
			return new RandomGhostMovementAlgorithm();
		case LINEAR:
			return new LinearGhostMovementAlgorithm();
		case NEAREST:
			return new NearestGhostMovementAlgorithm();
		default:
			return new RandomGhostMovementAlgorithm();
		}
	}
}
