package edu.sjsu.cs.ghost151;

import java.util.Random;

/**
 * <b>Game</b> will:
 * <ul>
 * <li>Configure the board
 * <li>Configure ghosts
 * <li>Configure the target
 * <li>Update the game
 * <li>Render the game
 * <li>Run the number of ghosts
 * <li>Print statistics at conclusion of game
 * </ul>
 * 
 * @author Alben Cheung
 * @author MD Ashfaqul Islam
 * @author Shaun Guth
 * @author Jerry Phul
 */
public enum Game {
	INSTANCE; // singleton

	private int numberOfCommunications;
	private int numberOfGhosts;
	private int numberOfGameLoops;
	private int numberOfMovements;
	private Ghost ghosts[];
	private Board board;
	private boolean isRunning;

	/**
	 * <b>Run</b> takes the number of Ghost objects to create and configures the
	 * board for simulation. At conclusion of simulation, the print statistics
	 * method will be called to display the results.
	 * 
	 * @param numberOfGhosts
	 *            number of Ghost objects to start the game with.
	 */
	public void Run(int numberOfGhosts) {
		this.numberOfGhosts = numberOfGhosts;

		ConfigureBoard();

		isRunning = true;

		while (isRunning) {
			Update();
			Render();
			++numberOfGameLoops;

			// slow down the game loop so that rendering is human-speed
			try {
				Thread.sleep(500);
			} catch (InterruptedException interruptedException) {
			}
		}

		PrintStatistics();
	}

	/**
	 * <b>PrintStatistics</b> responsible for printing the statistics.
	 */
	public void PrintStatistics() {
		System.out.println("Number of game loops: " + numberOfGameLoops);
		System.out.println("Number of movements: " + numberOfMovements);
		System.out.println("Number of communications: "
				+ numberOfCommunications);
	}

	/**
	 * <b>ConfigureBoard</b> will configure the number of defined or user
	 * entered ghosts and place them on the Board object.
	 */
	public void ConfigureBoard() {
		board = Board.INSTANCE;
		ConfigureGhosts();
		ConfigureTarget();
	}

	/**
	 * <b>ConfigureGhosts</b> will create the Ghost objects and place them at
	 * random positions on the Board object.
	 */
	private void ConfigureGhosts() {
		Random generator = new Random();

		ghosts = new Ghost[numberOfGhosts];

		for (int i = 0; i < numberOfGhosts; ++i) {
			ghosts[i] = new Ghost();

			// we always know that position 0,0 won't be empty (it's a wall)
			BoardPosition position = new BoardPosition(0, 0);

			while (board.GetObjectAt(position).getType() != BoardObjectType.Empty) {
				// pick a random position on the board for this ghost, avoid the
				// walls
				position.setRow(generator.nextInt(board.getRowCount() - 2) + 1);
				position.setColumn(generator.nextInt(board.getColumnCount() - 2) + 1);
			}

			board.SetObjectAt(position, ghosts[i]);
		}
	}

	/**
	 * <b>ConfigureTarget</b> move the Ghost object to a new random location.
	 */
	private void ConfigureTarget() {
		Random generator = new Random();

		// we always know that position 0,0 won't be empty (it's a wall)
		BoardPosition position = new BoardPosition(0, 0);

		while (board.GetObjectAt(position).getType() != BoardObjectType.Empty) {
			// pick a random position on the board for this ghost, avoid the
			// walls
			position.setRow(generator.nextInt(board.getRowCount() - 2) + 1);
			position.setColumn(generator.nextInt(board.getColumnCount() - 2) + 1);
		}

		BoardObject target = new BoardObject(BoardObjectType.Target);

		board.SetObjectAt(position, target);
	}

	/**
	 * <b>Update</b> will request from each Ghost object if the target (PacMan)
	 * has been acquired. If so, set <i>isRunning</i> to false so the Game loop
	 * may end.
	 */
	public void Update() {
		for (int i = 0; i < numberOfGhosts; ++i) {
			Ghost ghost = ghosts[i];
			ghost.Update();

			if (ghost.IsTargetAcquired()) {
				isRunning = false;
				return;
			}
		}
	}

	/**
	 * <b>Render</b> takes the board object, converts it to a string so that it
	 * may be printed on standard out.
	 */
	public void Render() {
		System.out.print(board.toString());
	}

	/**
	 * <b>IncrementCommunicationsCount</b> takes statistics on the number of
	 * Communications completed by all Ghost objects.
	 */
	public void IncrementCommunicationsCount() {
		++numberOfCommunications;
	}

	/**
	 * <b>IncrementMovementCount</b> takes statistics on the number of movements
	 * completed by all Ghost objects.
	 */
	public void IncrementMovementCount() {
		++numberOfMovements;
	}

	/**
	 * <b>getNumberOfCommunications</b> gets the number of communications.
	 * 
	 * @return the numberOfCommunications
	 */
	public int getNumberOfCommunications() {
		return numberOfCommunications;
	}

	/**
	 * <b>setNumberOfCommunications</b> allows the number of communications to
	 * be set.
	 * 
	 * @param numberOfCommunications
	 *            the numberOfCommunications to set
	 */
	public void setNumberOfCommunications(int numberOfCommunications) {
		this.numberOfCommunications = numberOfCommunications;
	}

	/**
	 * <b>getNumberOfGhosts</b> return the number of Ghost requested by user
	 * input.
	 * 
	 * @return the numberOfGhosts
	 */
	public int getNumberOfGhosts() {
		return numberOfGhosts;
	}

	/**
	 * <b>setNumberOfGhosts</b> allows the number of Ghost objects to be set.
	 * 
	 * @param numberOfGhosts
	 *            the numberOfGhosts to set
	 */
	public void setNumberOfGhosts(int numberOfGhosts) {
		this.numberOfGhosts = numberOfGhosts;
	}

	/**
	 * <b>getNumberOfMovements</b> the number of movements completed by all
	 * Ghost objects.
	 * 
	 * @return total number of movements.
	 */
	public int getNumberOfMovements() {
		return numberOfMovements;
	}

	/**
	 * <b>setNumberOfMovements</b> set the number of movements completed by all
	 * Ghost objects.
	 * 
	 * @param numberOfMovements
	 *            the numberOfMovements to set
	 */
	public void setNumberOfMovements(int numberOfMovements) {
		this.numberOfMovements = numberOfMovements;
	}

	/**
	 * <b>getGhosts</b> return the Ghost object.
	 * 
	 * @return the Ghost
	 */
	public Ghost[] getGhosts() {
		return ghosts;
	}

	/**
	 * <b>setGhosts</b> set the Ghost object.
	 * 
	 * @param ghosts
	 *            the Ghost object to set
	 */
	public void setGhosts(Ghost[] ghosts) {
		this.ghosts = ghosts;
	}

	/**
	 * <b>getBoard</b> get the Board object.
	 * 
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * <b>setBoard</b> set the Board object.
	 * 
	 * @param board
	 *            the Board object to set
	 */
	public void setBoard(Board board) {
		this.board = board;
	}
}
