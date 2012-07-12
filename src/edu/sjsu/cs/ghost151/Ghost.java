package edu.sjsu.cs.ghost151;

import java.util.ArrayList;
import java.util.Random;

/**
 * The <b>Ghost</b> is a specialized BoardObject that has the ability to look at
 * its surroundings, communicate with other ghosts, and move around the board.
 */
public class Ghost extends BoardObject {
	private GhostMovementAlgorithm movementAlgorithm;
	private BoardPosition goalMovePosition = null;
	private boolean exploredPositions[][];
	private boolean targetAcquired = false;
	private Random generator;

	/**
	 * Constructs a new Ghost with a new random number generator
	 */
	public Ghost() {
		this(new Random());
	}

	/**
	 * Constructs a new Ghost with the given random number generator and
	 * initializes the exploredPositions array
	 * 
	 * By default, a Ghost uses the LINEAR GhostMovementAlgorithmType
	 * 
	 * @param generator
	 *            The Random generator to use
	 */
	public Ghost(Random generator) {
		super(BoardObjectType.Ghost);

		this.generator = generator;

		exploredPositions = new boolean[Board.ROWS][Board.COLUMNS];

		movementAlgorithm = GhostMovementAlgorithmType.LINEAR.CreateInstance();
	}

	/**
	 * Part of the Update() loop - update our exploredPositions matrix based on
	 * our surroundings
	 */
	public void Scan() {
		BoardObject[] surroundings = GetSurroundings();

		for (BoardObject object : surroundings) {
			MarkAsExplored(object.getPosition());

			if (object.getType() == BoardObjectType.Target) {
				goalMovePosition = object.getPosition();
			}
		}
	}

	/**
	 * One-way communication between two Ghosts. "Downloads" the information
	 * from the given Ghost
	 * 
	 * @param ghost
	 *            the Ghost object to get information from
	 */
	public void CommunicateWith(Ghost ghost) {
		boolean[][] incoming = ghost.getExploredPositions();

		for (int row = 0; row < incoming.length; ++row) {
			for (int column = 0; column < incoming[row].length; ++column) {
				if (incoming[row][column] != false
						&& exploredPositions[row][column] == false) {
					exploredPositions[row][column] = incoming[row][column];
				}
			}
		}
	}

	/**
	 * Part of the Update() loop - moves towards the goalMovePosition (which is
	 * determined by the movementAlgorithm)
	 */
	public void Move() {
		if (goalMovePosition == null) {
			goalMovePosition = movementAlgorithm.DetermineNextPosition(this,
					generator);
		}

		// which direction will get us closer to our goalMovePosition?
		BoardDirection moveDirection = goalMovePosition.DirectionFrom(position);

		// what position is in that direction?
		BoardPosition movePosition = moveDirection.PositionFrom(position);

		// if we can't move that way, try just one component
		if (!AbleToMoveDirection(moveDirection)) {
			int rowOffset = moveDirection.getRowOffset();
			moveDirection = new BoardDirection(0,
					moveDirection.getColumnOffset());

			if (!AbleToMoveDirection(moveDirection)) {
				moveDirection = new BoardDirection(rowOffset, 0);
			}

			movePosition = moveDirection.PositionFrom(position);
		}

		MoveTo(movePosition);

		// pick a new position to move towards if our goal was explored
		if (exploredPositions[movePosition.getRow()][movePosition.getColumn()]) {
			goalMovePosition = movementAlgorithm.DetermineNextPosition(this,
					generator);
		}
	}

	/**
	 * Moves to the specified position, checks if we're acquiring the target and
	 * increments the game movement count statistics
	 * 
	 * @param newPosition
	 *            the object's new position
	 */
	private void MoveTo(BoardPosition newPosition) {
		if (null == newPosition || !board.IsValidMoveTarget(newPosition)) {
			return;
		}

		BoardObject targetObject = board.GetObjectAt(newPosition);

		// check if we're acquiring the target
		if (targetObject.getType() == BoardObjectType.Target) {
			targetAcquired = true;
		}

		BoardPosition oldPosition = this.getPosition();
		board.SetObjectAt(oldPosition, new BoardObject(BoardObjectType.Empty));
		board.SetObjectAt(newPosition, this);

		Game.INSTANCE.IncrementMovementCount();
	}

	/**
	 * Have we acquired the target?
	 * 
	 * @return true if the target has been acquired, false otherwise
	 */
	public boolean IsTargetAcquired() {
		return targetAcquired;
	}

	/**
	 * Mark a given position on the board as "explored"
	 * 
	 * @param position
	 *            the position to mark as explored
	 */
	private void MarkAsExplored(BoardPosition position) {
		exploredPositions[position.getRow()][position.getColumn()] = true;
	}

	/**
	 * Determine if we're able to move in the given direction from our current
	 * position
	 * 
	 * @param direction
	 *            the direction we want to know about
	 * @return true if we're able to move in that direction, false otherwise
	 */
	private boolean AbleToMoveDirection(BoardDirection direction) {
		BoardPosition position = direction.PositionFrom(this.position);

		if (board.IsValidMoveTarget(position)) {
			return true;
		}

		return false;
	}

	/**
	 * Retrieves our surroundings on the Board
	 * 
	 * @return an array of BoardObject for the positions around us.
	 */
	private BoardObject[] GetSurroundings() {
		ArrayList<BoardObject> surroundings = new ArrayList<BoardObject>();

		BoardPosition center = position;

		for (BoardDirection direction : BoardDirection.CompassDirections()) {
			BoardObject object = board.GetObjectAt(direction
					.PositionFrom(center));

			if (object != null) {
				surroundings.add(object);
			}
		}

		BoardObject[] surroundingsArray = new BoardObject[surroundings.size()];
		surroundings.toArray(surroundingsArray);

		return surroundingsArray;
	}

	/**
	 * Set the position of the board object.
	 * 
	 * @param position
	 *            the position to set
	 */
	@Override
	public void setPosition(BoardPosition position) {
		super.setPosition(position);
		MarkAsExplored(position);
	}

	/**
	 * Get the explored positions.
	 * 
	 * @return the explored positions array
	 */
	public boolean[][] getExploredPositions() {
		return exploredPositions;
	}

	/**
	 * Gets the movement algorithm this ghost is using
	 * 
	 * @return the movementAlgorithm being used by this ghost
	 */
	public GhostMovementAlgorithm getMovementAlgorithm() {
		return movementAlgorithm;
	}

	/**
	 * Sets the movement algorithm for this ghost to use
	 * 
	 * @param movementAlgorithm
	 *            the movementAlgorithm to set for this ghost
	 */
	public void setMovementAlgorithm(GhostMovementAlgorithm movementAlgorithm) {
		this.movementAlgorithm = movementAlgorithm;
	}
}
