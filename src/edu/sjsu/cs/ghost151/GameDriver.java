package edu.sjsu.cs.ghost151;

/**
 * <b>GameDriver</b> is responsible for instantiating the Game instance and
 * requesting the number of ghosts the user would like in the simulation.
 * 
 * @author Alben Cheung
 * @author MD Ashfaqul Islam
 * @author Shaun Guth
 * @author Jerry Phul
 */
public class GameDriver {

	/**
	 * <b>main</b> Initiates the Game instance.
	 * 
	 * @param args
	 *            Command line arguments.
	 */
	public static void main(String[] args) {
		Game game = Game.INSTANCE;
		int numberOfGhosts = 4;
		try {
			numberOfGhosts = Integer.valueOf(args[1]);
		}
		catch (Exception e) {
		}

		game.Run(numberOfGhosts);
	}
}
