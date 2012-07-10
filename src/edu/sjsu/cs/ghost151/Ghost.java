package edu.sjsu.cs.ghost151;

import java.util.Random;

/**
 * <b>Ghost</b> will communicate with other Ghosts to acquire a target and be
 * aware of areas already visited by each other. The Ghost will search for the
 * target (PacMan) and maintain a log of areas visited. If the target is
 * acquired, it will notify the Game.
 * 
 * @author Alben Cheung
 * @author MD Ashfaqul Islam
 * @author Shaun Guth
 * @author Jerry Phul
 */
public class Ghost extends BoardObject {
	private boolean exploredPositions[][];
	private boolean targetAcquired = false;
	private BoardObject[] surroundings = new BoardObject[0];
	private Random generator;
	private GhostMovementAlgorithm movementAlgorithm;
	private BoardPosition nextMovePosition = null;

	/**
	 * Construct a Ghost object that is aware of positions its explored.
	 */
	public Ghost() {
		this(new Random());
	}

	public Ghost(Random generator) {
		super(BoardObjectType.Ghost);
		this.generator = generator;
		exploredPositions = new boolean[Board.ROWS][Board.COLUMNS];

		movementAlgorithm = GhostMovementAlgorithmType.NEAREST.CreateInstance();
	}

	/**
	 * Part of the Update() loop - update our exploredPositions matrix
	 */
	public void Scan() {
		surroundings = board.GetSurroundings(this);

		for (BoardObject object : surroundings) {
			MarkAsExplored(object.getPosition());
		}
	}

	/**
	 * Synchronize places explored between Ghost objects.
	 * 
	 * @param ghost
	 *            the Ghost object to communicate with
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
	 * Part of the Update() loop - moves to the targeted position
	 * 
	 * @Precondition Requires Scan() to have updated the surroundings array
	 */
	public void Move() {
		// first, look in our vicinity for the target, go to it if found.
		for (BoardObject object : surroundings) {
			if (object.getType() == BoardObjectType.Target) {
				MoveTo(object.getPosition());
				return;
			}
		}

		// if we've explored our last target position (or been told about it by
		// another ghost) then pick a new one
		if (nextMovePosition == null
				|| exploredPositions[nextMovePosition.getRow()][nextMovePosition
						.getColumn()]) {
			nextMovePosition = movementAlgorithm.DetermineNextPosition(this,
					generator);
		}

		// after that, figure out how to get closer to it
		BoardDirection nextMoveDirection = nextMovePosition
				.DirectionFrom(position);

		// if we can't move in that direction
		// try to move horizontally or vertically instead
		if (!AbleToMoveDirection(nextMoveDirection)) {
			int rowOffset = nextMoveDirection.getRowOffset();
			nextMoveDirection.setRowOffset(BoardDirection.ROW_OFFSET_STAYPUT);
			if (!AbleToMoveDirection(nextMoveDirection)) {
				nextMoveDirection.setRowOffset(rowOffset);
				nextMoveDirection
						.setColumnOffset(BoardDirection.COLUMN_OFFSET_STAYPUT);
			}
		}

		// then move that way
		MoveTo(nextMoveDirection.PositionFrom(position));
	}

	/**
	 * Move an object from it's current position to a new position.
	 * 
	 * @param newPosition
	 *            the object's new position
	 */
	private void MoveTo(BoardPosition newPosition) {
		BoardObject targetObject = board.GetObjectAt(newPosition);

		if (null == newPosition || !targetObject.IsValidMoveTarget()) {
			return;
		}

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
	 * returns a boolean value if the target was acquired by the object.
	 * 
	 * @return boolean value
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
	 * @return true if we're able to move in that direction
	 * @return false otherwise
	 */
	private boolean AbleToMoveDirection(BoardDirection direction) {
		BoardPosition targetPosition = direction.PositionFrom(position);

		if (board.GetObjectAt(targetPosition).IsValidMoveTarget()) {
			return true;
		}

		return false;
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
	 * @return the movementAlgorithm
	 */
	public GhostMovementAlgorithm getMovementAlgorithm() {
		return movementAlgorithm;
	}

	/**
	 * @param movementAlgorithm
	 *            the movementAlgorithm to set
	 */
	public void setMovementAlgorithm(GhostMovementAlgorithm movementAlgorithm) {
		this.movementAlgorithm = movementAlgorithm;
	}
}
