/**
 * 
 */
package edu.sjsu.cs.ghost151;

import java.util.Random;

/**
 * @author shaung
 *
 */
public interface GhostMovementAlgorithm {
	BoardPosition DetermineNextPosition(Ghost ghost);
	BoardPosition DetermineNextPosition(Ghost ghost, Random generator);
}
