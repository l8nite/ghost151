package edu.sjsu.cs.ghost151;

import java.util.ArrayList;
import java.util.HashMap;
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
		// first, look within our immediate vicinity for the target, go to it if
		// found.
		for (BoardObject object : surroundings) {
			if (object.getType() == BoardObjectType.Target) {
				MoveTo(object.getPosition());
				return;
			}
		}

		// otherwise, figure out a new target we want to move towards
		BoardPosition nextMovePosition = DetermineNextMovePosition();

		// then figure out how to get closer to it
		BoardDirection nextMoveDirection = DirectionTowards(nextMovePosition);

		// then move that way
		MoveTo(nextMoveDirection.PositionFrom(this.position));
	}

	/**
	 * Looks radially outward from our position to determine the "nearest"
	 * unexplored space in a given direction. Failing that, randomly chooses one
	 * of the unexplored spaces remaining on the board and moves towards that.
	 * 
	 * @return a position that we want to move towards
	 */
	private BoardPosition DetermineNextMovePosition() {
		HashMap<BoardDirection, ScoredBoardPosition> scores = new HashMap<BoardDirection, ScoredBoardPosition>();

		ArrayList<ScoredBoardPosition> tiedChoices = new ArrayList<ScoredBoardPosition>();
		int minimumScore = Integer.MAX_VALUE;

		for (BoardDirection direction : BoardDirection.values()) {
			if (direction == BoardDirection.STAYPUT) {
				continue;
			}
			
			ScoredBoardPosition scoredPosition = LinearCountExploredPositions(direction);
			scores.put(direction, scoredPosition);
			
			int score = scoredPosition.getScore();

			if (score != -1 && score < minimumScore) {
				minimumScore = score;
				tiedChoices.clear();
				tiedChoices.add(scoredPosition);
			} else if (score != -1 && score == minimumScore) {
				tiedChoices.add(scoredPosition);
			}
		}
		
		BoardPosition targetPosition = null;

		if (tiedChoices.size() > 0) {
			int randomTieBreakerIndex = generator.nextInt(tiedChoices.size());
			targetPosition  = tiedChoices.get(randomTieBreakerIndex);
		}

		// if we couldn't find an unexplored space in any of the 8 directions
		// then we'll just pick a remaining unexplored space at random
		if (targetPosition == null) {
			ArrayList<BoardPosition> unexplored = new ArrayList<BoardPosition>();
			for (int row = 0; row < Board.ROWS; ++row) {
				for (int column = 0; column < Board.COLUMNS; ++column) {
					if (exploredPositions[row][column] == false) {
						unexplored.add(new BoardPosition(row, column));
					}
				}
			}

			int randomUnexploredIndex = generator.nextInt(unexplored.size());
			targetPosition = unexplored.get(randomUnexploredIndex);
		}
		
		return targetPosition;
	}

	/**
	 * Count the number of unexplored positions in a straight direction from our
	 * position
	 * 
	 * @param direction
	 *            The direction to count
	 * @return A "scored" BoardPosition where the score is the number of
	 *         explored spaces that would be traversed before reaching an
	 *         unexplored space. The score is -1 if there are no unexplored
	 *         spaces in the given direction
	 */
	private ScoredBoardPosition LinearCountExploredPositions(
			BoardDirection direction) {
		int row = position.getRow();
		int column = position.getColumn();

		int dx = direction.getColumnOffset();
		int dy = direction.getRowOffset();

		int exploredPositionCount = 0;

		while ((row + dy) >= 0 && (row + dy) < Board.ROWS && (column + dx) >= 0
				&& (column + dx) < Board.COLUMNS) {
			row += dy;
			column += dx;

			if (exploredPositions[row][column] == false) {
				break;
			}

			++exploredPositionCount;
		}

		ScoredBoardPosition scoredBoardPosition = new ScoredBoardPosition(row,
				column, exploredPositionCount);

		// if we ended up hitting a boundary, then there are no unexplored
		// positions in this direction --
		if (exploredPositions[row][column] != false) {
			scoredBoardPosition.setScore(-1);
		}

		scoredBoardPosition.setLabel(direction.toString());
		return scoredBoardPosition;
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
	 * Determine a BoardDirection that gets us closer to targetPosition given
	 * the constraints of the board (edges, etc).
	 * 
	 * @param targetPosition
	 *            the position we want to move towards
	 * @return the direction the ghost should move
	 */
	private BoardDirection DirectionTowards(BoardPosition targetPosition) {
		// and then move towards it
		BoardDirection moveDirection = BoardDirection.STAYPUT;

		if (targetPosition.getRow() > targetPosition.getRow()) {
			moveDirection.setRowOffset(BoardDirection.ROW_OFFSET_DOWN);
		} else if (targetPosition.getRow() < targetPosition.getRow()) {
			moveDirection.setRowOffset(BoardDirection.ROW_OFFSET_UP);
		}

		// if we can't move up or down from where we are, then we need to stay
		// put
		if (!AbleToMoveDirection(moveDirection)) {
			moveDirection.setRowOffset(BoardDirection.ROW_OFFSET_STAYPUT);
		}

		if (targetPosition.getColumn() > targetPosition.getColumn()) {
			moveDirection.setColumnOffset(BoardDirection.COLUMN_OFFSET_RIGHT);
		} else if (targetPosition.getColumn() < targetPosition.getColumn()) {
			moveDirection.setColumnOffset(BoardDirection.COLUMN_OFFSET_LEFT);
		}

		// if we can't move left or right from where we are, then we need to
		// stay put
		if (!AbleToMoveDirection(moveDirection)) {
			moveDirection.setRowOffset(BoardDirection.ROW_OFFSET_STAYPUT);
		}

		return moveDirection;
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
}
