/**
 * 
 */
package edu.sjsu.cs.ghost151;

/**
 * <b>ScoredBoardPosition</b stores a score along with position information which can be used
 * for determining movement direction, etc.
 * 
 * @author Alben Cheung
 * @author MD Ashfaqul Islam
 * @author Shaun Guth
 * @author Jerry Phul
 */
public class ScoredBoardPosition extends BoardPosition {
	
	public ScoredBoardPosition(int row, int column) {
		this(row, column, -1);
	}
	
	public ScoredBoardPosition(int row, int column, int score) {
		super(row, column);
		this.score = score;
		this.label = "";
	}

	private int score;
	private String label;

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return (label.isEmpty() ? "" : label + ": " ) + "(" + row + ", " + column + "): " + score;
	}
}
