package edu.sjsu.cs.ghost151;

import java.util.Random;

/**
 * A <b>GhostMovementAlgorithm</b> uses a Ghost's explored positions and other
 * information a Ghost has to determine the next position on the map the Ghost
 * should move towards.
 */
public interface GhostMovementAlgorithm {
	BoardPosition DetermineNextPosition(Ghost ghost);
	BoardPosition DetermineNextPosition(Ghost ghost, Random generator);
}
