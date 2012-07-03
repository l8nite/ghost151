/**
 * 
 */
package edu.sjsu.cs.ghost151;

/**
 * @author shaung
 *
 */
public class Ghost extends BoardObject {
	private BoardPosition exploredPositions[];

	/**
	 * @return the exploredPositions
	 */
	public BoardPosition[] getExploredPositions() {
		return exploredPositions;
	}

	/**
	 * @param exploredPositions the exploredPositions to set
	 */
	public void setExploredPositions(BoardPosition[] exploredPositions) {
		this.exploredPositions = exploredPositions;
	}
	
}
