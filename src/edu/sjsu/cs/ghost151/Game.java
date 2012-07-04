/**
 * 
 */
package edu.sjsu.cs.ghost151;

/**
 * @author shaung
 *
 */
public class Game {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * @return the numberOfCommunications
	 */
	public int getNumberOfCommunications() {
		return numberOfCommunications;
	}
	/**
	 * @param numberOfCommunications the numberOfCommunications to set
	 */
	public void setNumberOfCommunications(int numberOfCommunications) {
		this.numberOfCommunications = numberOfCommunications;
	}
	/**
	 * @return the numberOfGhosts
	 */
	public int getNumberOfGhosts() {
		return numberOfGhosts;
	}
	/**
	 * @param numberOfGhosts the numberOfGhosts to set
	 */
	public void setNumberOfGhosts(int numberOfGhosts) {
		this.numberOfGhosts = numberOfGhosts;
	}
	/**
	 * @return the numberOfMovements
	 */
	public int getNumberOfMovements() {
		return numberOfMovements;
	}
	/**
	 * @param numberOfMovements the numberOfMovements to set
	 */
	public void setNumberOfMovements(int numberOfMovements) {
		this.numberOfMovements = numberOfMovements;
	}
	/**
	 * @return the ghosts
	 */
	public Ghost[] getGhosts() {
		return ghosts;
	}
	/**
	 * @param ghosts the ghosts to set
	 */
	public void setGhosts(Ghost[] ghosts) {
		this.ghosts = ghosts;
	}
	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}
	/**
	 * @param board the board to set
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

	private int numberOfCommunications;
	private int numberOfGhosts;
	private int numberOfMovements;
	private Ghost ghosts[];
	private Board board;
}
