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
	private BoardObjectType exploredPositions[][];
	private boolean targetAcquired = false;
	private BoardObject[] surroundings = new BoardObject[0];
	private BoardPosition nextPosition = null; // where we want to go next
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
		exploredPositions = new BoardObjectType[Board.ROWS][Board.COLUMNS];
	}

	/**
	 * Get the explored positions.
	 * 
	 * @return the explored positions array
	 */
	public BoardObjectType[][] getExploredPositions() {
		return exploredPositions;
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
	 * Synchronize places explored between Ghost objects.
	 * 
	 * @param ghost
	 *            the Ghost object to communicate with
	 */
	public void CommunicateWith(Ghost ghost) {
		BoardObjectType[][] incoming = ghost.getExploredPositions();

		for (int row = 0; row < incoming.length; ++row) {
			for (int column = 0; column < incoming[row].length; ++column) {
				if (incoming[row][column] != null
						&& exploredPositions[row][column] == null) {
					exploredPositions[row][column] = incoming[row][column];
				}
			}
		}
		
		DetermineNextMovePosition();
	}

	/**
	 * Part of the Update() loop - update our exploredPositions matrix
	 */
	public void Scan() {
		surroundings = board.GetSurroundings(this);

		for (BoardObject object : surroundings) {
			BoardPosition position = object.getPosition();
			BoardObjectType type = object.getType();

			exploredPositions[position.getRow()][position.getColumn()] = type;
		}
	}

	/**
	 * Part of the Update() loop - communicates with any ghosts in the
	 * surroundings
	 * 
	 * @Precondition Requires Scan() to have updated the surroundings array
	 */
	public void Communicate() {
		for (BoardObject object : surroundings) {
			if (object.getType() == BoardObjectType.Ghost) {
				// communicate with this ghost and vice-versa
				this.CommunicateWith((Ghost) object);
				Game.INSTANCE.IncrementCommunicationsCount();
			}
		}

		// we got new information, pick a new direction based on it.
		DetermineNextMovePosition();
	}

	/**
	 * Part of the Update() loop - moves to the targeted position
	 * 
	 * @Precondition Requires Scan() to have updated the surroundings array
	 */
	public void Move() {
		// first, look within our immediate vicinity for the target, go to it.
		for (BoardObject object : surroundings) {
			if (object.getType() == BoardObjectType.Target) {
				MoveTo(object.getPosition());
				return;
			}
		}

		// if we reached our objective on the last move, pick a new objective
		if (nextPosition == null) {
			DetermineNextMovePosition();
		}

		// move towards nextPosition
		int dx = 0;
		int dy = 0;

		if (nextPosition.getRow() > position.getRow()) {
			dy = 1; // move down
		} else if (nextPosition.getRow() < position.getRow()) {
			dy = -1; // move up
		}

		if (nextPosition.getColumn() > position.getColumn()) {
			dx = 1; // move right
		} else if (nextPosition.getColumn() < position.getColumn()) {
			dx = -1; // move left
		}

		BoardPosition moveToPosition = new BoardPosition(
				position.getRow() + dy, position.getColumn() + dx);

		if (moveToPosition.compareTo(nextPosition) == 0) {
			nextPosition = null; // reached our stated goal
		}

		// if we're attempting to move into a wall, pick a new direction for
		// next time
		if (board.GetObjectAt(moveToPosition).getType() == BoardObjectType.Wall) {
			nextPosition = null;
		}

		MoveTo(moveToPosition);
	}

	private void DetermineNextMovePosition() {
		// next, look outward linearly from our current position and move to the
		// nearest unexplored space

		HashMap<String, ScoredBoardPosition> scores = new HashMap<String, ScoredBoardPosition>();

		scores.put("UP", LinearCountExploredPositions(0, -1));
		scores.put("LEFT", LinearCountExploredPositions(-1, 0));
		scores.put("DOWN", LinearCountExploredPositions(0, 1));
		scores.put("RIGHT", LinearCountExploredPositions(1, 0));
		scores.put("UP, LEFT", LinearCountExploredPositions(-1, -1));
		scores.put("DOWN, LEFT", LinearCountExploredPositions(-1, 1));
		scores.put("UP, RIGHT", LinearCountExploredPositions(1, -1));
		scores.put("DOWN, RIGHT", LinearCountExploredPositions(1, 1));

		int minimumScore = Integer.MAX_VALUE;

		ArrayList<ScoredBoardPosition> tiedChoices = new ArrayList<ScoredBoardPosition>();

		for (String direction : scores.keySet()) {
			ScoredBoardPosition scoredBoardPosition = scores.get(direction);
			scoredBoardPosition.setLabel(direction);

			int score = scoredBoardPosition.getScore();
			if (score != -1 && score < minimumScore) {
				minimumScore = score;
				tiedChoices.clear();
				tiedChoices.add(scoredBoardPosition);
			} else if (score != -1 && score == minimumScore) {
				tiedChoices.add(scoredBoardPosition);
			}
		}

		if (tiedChoices.size() > 0) {
			int randomTieBreakerIndex = generator.nextInt(tiedChoices.size());
			nextPosition = tiedChoices.get(randomTieBreakerIndex);
		}

		// if we couldn't find an unexplored space in any of the 8 directions
		// then we'll just pick one at random
		while (nextPosition == null) {
			int randomSurroundingIndex = generator.nextInt(surroundings.length);
			BoardObject randomSurrounding = surroundings[randomSurroundingIndex];
			if (randomSurrounding.getType() == BoardObjectType.Empty) {
				nextPosition = randomSurrounding.getPosition();
			}
		}
	}

	private ScoredBoardPosition LinearCountExploredPositions(int dx, int dy) {
		int row = position.getRow();
		int column = position.getColumn();

		int exploredPositionCount = 0;

		while ((row + dy) >= 0 && (row + dy) < Board.ROWS && (column + dx) >= 0
				&& (column + dx) < Board.COLUMNS) {
			row += dy;
			column += dx;

			if (exploredPositions[row][column] == null) {
				break;
			}

			++exploredPositionCount;
		}

		ScoredBoardPosition scoredBoardPosition = new ScoredBoardPosition(row,
				column, exploredPositionCount);

		// if we ended up hitting a boundary, then there are no unexplored
		// positions in this direction --
		if (exploredPositions[row][column] != null) {
			scoredBoardPosition.setScore(-1);
		}

		return scoredBoardPosition;
	}

	/**
	 * Move an object from it's current position to a new position.
	 * 
	 * @param newPosition
	 *            the object's new position
	 */
	private void MoveTo(BoardPosition newPosition) {
		if (null == newPosition) {
			return;
		}

		// we can't move into anything but empty space or the target
		BoardObjectType newPositionType = board.GetObjectAt(newPosition)
				.getType();
		if (newPositionType != BoardObjectType.Empty
				&& newPositionType != BoardObjectType.Target) {
			return;
		}

		// check if we're acquiring the target
		if (newPositionType == BoardObjectType.Target) {
			targetAcquired = true;
		}

		BoardPosition oldPosition = this.getPosition();
		board.SetObjectAt(oldPosition, new BoardObject(BoardObjectType.Empty));
		board.SetObjectAt(newPosition, this);

		Game.INSTANCE.IncrementMovementCount();
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
		exploredPositions[position.getRow()][position.getColumn()] = BoardObjectType.Ghost;
	}
}
