package edu.sjsu.cs.ghost151;

import java.util.ArrayList;
import java.util.Random;

/**
 * The <b>Game</b> class contains the core logic for our simulation.
 * 
 * It has three states: Initializing, Running, Finishing
 * 
 * During Initialization, the Game will:
 * <ul>
 * <li>Configure the board</li>
 * <li>Configure ghosts</li>
 * <li>Configure the target</li>
 * </ul>
 * 
 * After a call to Run(), the Game will enter Running state, which loops
 * repeatedly until a Ghost reports that the target is acquired.
 * 
 * One loop will:
 * <ul>
 * <li>Instruct Ghosts to Move</li>
 * <li>Instruct Ghosts to Scan</li>
 * <li>Instruct Ghosts to Communicate</li>
 * <li>Render the Board</li>
 * </ul>
 * 
 * After the target is acquired, the Game will enter the Finishing state, where
 * it will:
 * <ul>
 * <li>Print the game statistics</li>
 * <li>Exit the game</li>
 * </ul>
 */
public enum Game {
	INSTANCE; // singleton

	private int numberOfGhosts;

	// statistics
	private int numberOfCommunications;
	private int numberOfGameLoops;
	private int numberOfMovements;

	private Ghost ghosts[];
	private Board board;

	private boolean isRunning;

	/**
	 * Initializes the main game board, creates the specified number of Ghosts
	 * and places them on the board, and also creates and places the target.
	 * 
	 * @param numberOfGhosts
	 *            The number of ghosts to place on the board
	 * @param generator
	 *            A Random generator used for placing the ghosts/target on the
	 *            board
	 */
	public void ConfigureBoard(int numberOfGhosts, Random generator) {
		board = Board.INSTANCE;
		board.Initialize();

		this.numberOfGhosts = numberOfGhosts;
		numberOfCommunications = 0;
		numberOfMovements = 0;
		numberOfGameLoops = 0;

		ConfigureTarget(generator);
		ConfigureGhosts(generator);
	}

	/**
	 * Create the Ghost objects and place them at random positions on the board
	 * 
	 * @param generator
	 *            The Random generator to use for placing the ghosts
	 */
	private void ConfigureGhosts(Random generator) {
		ghosts = new Ghost[numberOfGhosts];

		ArrayList<BoardObject> empties = new ArrayList<BoardObject>();

		for (BoardObject[] row : board.getGrid()) {
			for (BoardObject object : row) {
				if (object.getType() == BoardObjectType.Empty) {
					empties.add(object);
				}
			}
		}

		for (int i = 0; i < numberOfGhosts; ++i) {
			ghosts[i] = new Ghost();

			BoardObject empty = empties.get(generator.nextInt(empties.size()));
			empties.remove(empty);

			board.SetObjectAt(empty.getPosition(), ghosts[i]);
		}
	}

	/**
	 * Puts a new Target object on the board
	 */
	private void ConfigureTarget(Random generator) {
		ArrayList<BoardObject> empties = new ArrayList<BoardObject>();

		for (BoardObject[] row : board.getGrid()) {
			for (BoardObject object : row) {
				if (object.getType() == BoardObjectType.Empty) {
					empties.add(object);
				}
			}
		}

		BoardObject empty = empties.get(generator.nextInt(empties.size()));
		empties.remove(empty);

		BoardObject target = new BoardObject(BoardObjectType.Target);
		board.SetObjectAt(empty.getPosition(), target);
	}

	/**
	 * Runs the simulation with the given number of ghosts
	 * 
	 * @param numberOfGhosts
	 *            number of ghosts that will work to find the target
	 */
	public void Run(int numberOfGhosts) {
		Random generator = new Random();
		ConfigureBoard(numberOfGhosts, generator);

		isRunning = true;

		Render();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException interruptedException) {
		}

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
	 * Let the ghosts move, scan their surroundings, and communicate. If any
	 * ghost finds the target, return right away and set a flag so that we can
	 * terminate
	 */
	public void Update() {
		for (Ghost ghost : ghosts) {
			ghost.Move();

			if (ghost.IsTargetAcquired()) {
				isRunning = false;
				return;
			}
		}

		for (Ghost ghost : ghosts) {
			ghost.Scan();
		}

		for (Ghost ghost1 : ghosts) {
			for (Ghost ghost2 : ghosts) {
				if (ghost1 != ghost2) {
					ghost1.CommunicateWith(ghost2);
					ghost2.CommunicateWith(ghost1);
					IncrementCommunicationsCount();
				}
			}
		}
	}

	/**
	 * End the simulation at the end of the next Update() loop
	 */
	public void Terminate() {
		isRunning = false;
	}

	/**
	 * Draws the board to stdout
	 */
	public void Render() {
		System.out.print(board.toString());
	}

	/**
	 * Gets a string representing the final game statistics.
	 * 
	 * @return The statistics in a formatted string
	 */
	public String GetStatistics() {
		return "Number of game loops: " + numberOfGameLoops + "\n"
				+ "Number of movements: " + numberOfMovements + "\n"
				+ "Number of communications: " + numberOfCommunications + "\n";
	}

	/**
	 * Increments the communication counter
	 */
	public void IncrementCommunicationsCount() {
		++numberOfCommunications;
	}

	/**
	 * Increments the movement counter
	 */
	public void IncrementMovementCount() {
		++numberOfMovements;
	}

	/**
	 * Gets the number of communications
	 * 
	 * @return The communication count
	 */
	public int getNumberOfCommunications() {
		return numberOfCommunications;
	}

	/**
	 * Gets the number of movements
	 * 
	 * @return The movement count
	 */
	public int getNumberOfMovements() {
		return numberOfMovements;
	}

	/**
	 * Gets the number of game loops completed
	 * 
	 * @return The game loop counter
	 */
	public int getNumberOfGameLoops() {
		return numberOfGameLoops;
	}

	/**
	 * Get the ghosts being used currently
	 * 
	 * @return The Ghosts currently being used in this simulation
	 */
	public Ghost[] getGhosts() {
		return ghosts;
	}
}
