package edu.sjsu.cs.ghost151;

/**
 * <b>GameDriver</b> is responsible for instantiating the Game instance and
 * requesting the number of ghosts the user would like in the simulation.
 */
public class GameDriver {

	/**
	 * Starts the simulation
	 * 
	 * @param args
	 *            The first argument is optional and can be an integer
	 *            representing the number of ghosts to place on the board.
	 * 
	 *            The maximum number of ghosts is the number of empty spaces on
	 *            the Board minus 1 (for the target). For the standard board
	 *            configuration (10x20), that is 143.
	 */
	public static void main(String[] args) {
		Game game = Game.INSTANCE;

		int numberOfGhosts = 4;
		try {
			numberOfGhosts = Integer.valueOf(args[1]);
		} catch (Exception e) {
		}

		Board board = game.getBoard();

		int maximumGhosts = board.ROWS * board.COLUMNS - 2 * board.ROWS - 2
				* (board.COLUMNS - 2) - 1;

		if (numberOfGhosts > maximumGhosts) {
			System.out.println("The maximum # of ghosts is: " + maximumGhosts);
			return;
		}

		if (numberOfGhosts < 1) {
			System.out.println("Need at least 1 ghost on the board");
			return;
		}

		game.Run(numberOfGhosts);
	}
}
