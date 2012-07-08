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
	 * <b>ConfigureBoard</b> will configure the number of defined or user
	 * entered ghosts and place them on the Board object.
	 */
	public void ConfigureBoard(int numberOfGhosts, Random generator) {
		board = Board.INSTANCE;
		board.Initialize();

		this.numberOfGhosts = numberOfGhosts;
		numberOfCommunications = 0;
		numberOfMovements = 0;
		numberOfGameLoops = 0;

		ConfigureGhosts(generator);
		ConfigureTarget(generator);
	}

	/**
	 * <b>ConfigureGhosts</b> will create the Ghost objects and place them at
	 * random positions on the Board object.
	 */
	private void ConfigureGhosts(Random generator) {
		ghosts = new Ghost[numberOfGhosts];

		for (int i = 0; i < numberOfGhosts; ++i) {
			ghosts[i] = new Ghost(generator);

			// we always know that position 0,0 won't be empty (it's a wall)
			BoardPosition position = new BoardPosition(0, 0);

			while (board.GetObjectAt(position).getType() != BoardObjectType.Empty) {
				// pick a random position on the board for this ghost, avoid the
				// walls
				position.setRow(generator.nextInt(Board.ROWS - 2) + 1);
				position.setColumn(generator.nextInt(Board.COLUMNS - 2) + 1);
			}

			board.SetObjectAt(position, ghosts[i]);
		}
	}

	/**
	 * <b>ConfigureTarget</b> move the Pacman object to a new random location.
	 */
	private void ConfigureTarget(Random generator) {
		// we always know that position 0,0 won't be empty (it's a wall)
		BoardPosition position = new BoardPosition(0, 0);

		while (board.GetObjectAt(position).getType() != BoardObjectType.Empty) {
			// pick a random position on the board, avoid the walls
			position.setRow(generator.nextInt(Board.ROWS - 2) + 1);
			position.setColumn(generator.nextInt(Board.COLUMNS - 2) + 1);
		}

		BoardObject target = new BoardObject(BoardObjectType.Target);

		board.SetObjectAt(position, target);
	}

	/**
	 * <b>Run</b> takes the number of Ghost objects to create and configures the
	 * board for simulation. At conclusion of simulation, the print statistics
	 * method will be called to display the results.
	 * 
	 * @param numberOfGhosts
	 *            number of Ghost objects to start the game with.
	 */
	public void Run(int numberOfGhosts) {
		Random generator = new Random();
		ConfigureBoard(numberOfGhosts, generator);

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

		System.out.println(GetStatistics());
	}

	/**
	 * <b>Update</b> will request from each Ghost object if the target (PacMan)
	 * has been acquired. If so, set <i>isRunning</i> to false so the Game loop
	 * may end.
	 */
	public void Update() {
		for (int i = 0; i < numberOfGhosts; ++i) {
			Ghost ghost = ghosts[i];
			ghost.Scan();
		}

		for (int i = 0; i < numberOfGhosts; ++i) {
			Ghost ghost = ghosts[i];
			ghost.Move();

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
	 * <b>GetStatistics</b> returns a string representing the final game
	 * statistics.
	 * 
	 * @return the string to print for statistics
	 */
	public String GetStatistics() {
		return "Number of game loops: " + numberOfGameLoops + "\n"
				+ "Number of movements: " + numberOfMovements + "\n"
				+ "Number of communications: " + numberOfCommunications + "\n";
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
	 * <b>getNumberOfCommunications</b> the number of communications completed
	 * by all Ghost objects.
	 * 
	 * @return total number of communications.
	 */
	public int getNumberOfCommunications() {
		return numberOfCommunications;
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
	 * <b>getNumberOfGameLoops</b> the number of game loops completed
	 * 
	 * @return total number of game loops
	 */
	public int getNumberOfGameLoops() {
		return numberOfGameLoops;
	}

	/**
	 * <b>getGhosts</b> return the Ghost object.
	 * 
	 * @return the Ghost
	 */
	public Ghost[] getGhosts() {
		return ghosts;
	}
}
