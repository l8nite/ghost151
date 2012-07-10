/**
 * 
 */
package edu.sjsu.cs.ghost151;

/**
 * @author shaung
 *
 */
public enum GhostMovementAlgorithmType {
	RANDOM,
	LINEAR,
	NEAREST;
	
	public GhostMovementAlgorithm CreateInstance() {
		switch (this) {
		case RANDOM:
			return new RandomGhostMovementAlgorithm();
		case LINEAR:
			return new LinearGhostMovementAlgorithm();
		case NEAREST:
			return new NearestUnexploredSpaceGhostMovementAlgorithm();
		default:
			return new RandomGhostMovementAlgorithm();
		}
	}
}
