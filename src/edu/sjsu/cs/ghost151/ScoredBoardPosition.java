/**
 * 
 */
package edu.sjsu.cs.ghost151;

/**
 * A <b>ScoredBoardPosition</b stores a score along with position information
 * which can be used for determining movement direction, etc.
 */
public class ScoredBoardPosition extends BoardPosition {
	private int score;

	/**
	 * Creates a new ScoredBoardPosition with the given row and column. The
	 * score is set to -1 by default
	 * 
	 * @param row
	 *            The row for this position
	 * @param column
	 *            The column for this position
	 */
	public ScoredBoardPosition(int row, int column) {
		this(row, column, -1);
	}

	/**
	 * Creates a new ScoredBoardPosition with the given row, column, and score.
	 * 
	 * @param row
	 *            The row for this position
	 * @param column
	 *            The column for this position
	 * @param score
	 *            The score for this position
	 */
	public ScoredBoardPosition(int row, int column, int score) {
		super(row, column);
		this.score = score;
	}

	/**
	 * Get the score for this position
	 * 
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Set the score for this position
	 * 
	 * @param score
	 *            the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}
}
