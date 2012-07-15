package edu.sjsu.cs.ghost151;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Alben Cheung
 * @author MD Ashfaqul Islam
 * @author Shaun Guth
 * @author Jerry Phul
 */
public class GameTest {

	private Game game;
	private Board board;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		game = Game.INSTANCE;
		board = game.getBoard();
	}
	
	@Test
	public void testRun() {
		Thread.currentThread().interrupt();
		game.getBoard().setRenderer(new InvisibleBoardRenderer());
		game.Run(4);
	}
	
	@Test
	public void testSingleton() {
		assertEquals(Game.INSTANCE, Game.valueOf("INSTANCE"));
	}

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.Game#ConfigureBoard(int numberOfGhosts, Random generator)}.
	 */
	@Test
	public void testConfigureBoard() {
		Random generator = new Random(42);

		game.ConfigureBoard(1, generator);
	
		BoardPosition ghostPosition = board.BoardPosition(4, 7);
		assertEquals(BoardObjectType.Ghost, board.GetObjectAt(ghostPosition)
				.getType());

		// test that the target made it to the right spot
		BoardPosition targetPosition = board.BoardPosition(2, 9);
		assertEquals(BoardObjectType.Target, board.GetObjectAt(targetPosition)
				.getType());

		// test that the right number of elements were placed on the board
		Map<BoardObjectType, Integer> expectedObjectTypeCounts = new HashMap<BoardObjectType, Integer>();
		Map<BoardObjectType, Integer> actualObjectTypeCounts = new HashMap<BoardObjectType, Integer>();

		expectedObjectTypeCounts.put(BoardObjectType.Ghost, 1);
		expectedObjectTypeCounts.put(BoardObjectType.Target, 1);
		expectedObjectTypeCounts.put(BoardObjectType.Wall, 56);
		expectedObjectTypeCounts.put(BoardObjectType.Empty, 142);

		BoardObject[][] grid = board.getGrid();

		for (int row = 0; row < board.ROWS; ++row) {
			for (int column = 0; column < board.COLUMNS; ++column) {
				BoardObjectType type = grid[row][column].getType();
				Integer count = actualObjectTypeCounts.get(type);
				if (count == null) {
					count = 0;
				}
				actualObjectTypeCounts.put(type, count + 1);
			}
		}

		for (BoardObjectType type : BoardObjectType.values()) {
			assertEquals(type.toString(), expectedObjectTypeCounts.get(type),
					actualObjectTypeCounts.get(type));
		}
	}

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.Game#Update()}.
	 */
	@Test
	public void testUpdate() {
		// set the board up in a particular way and run one Update() loop
		// see that all ghosts have moved/communicated/acquired the pac-man
		Random generator = new Random(35);
		board.Initialize();
		game.ConfigureBoard(4, generator);
		
		game.Update();
		
		Ghost[] ghosts = game.getGhosts();

		assertEquals(false, ghosts[3].IsTargetAcquired());
		assertEquals(false, ghosts[2].IsTargetAcquired());
		assertEquals(false, ghosts[1].IsTargetAcquired());
		assertEquals(true, ghosts[0].IsTargetAcquired());

		assertEquals(0, game.getNumberOfCommunications());
		assertEquals(1, game.getNumberOfMovements());
	}

	/**
	 * Test method for {@link edu.sjsu.cs.ghost151.Game#GetStatistics()}.
	 */
	@Test
	public void testGetStatistics() {
		int numberOfGameLoops = game.getNumberOfGameLoops();
		int numberOfMovements = game.getNumberOfMovements();
		int numberOfCommunications = game.getNumberOfCommunications();

		String expected = "Number of game loops: " + numberOfGameLoops + "\n"
				+ "Number of movements: " + numberOfMovements + "\n"
				+ "Number of communications: " + numberOfCommunications + "\n";

		assertEquals(expected, game.GetStatistics());
	}

	/**
	 * Test method for
	 * {@link edu.sjsu.cs.ghost151.Game#IncrementCommunicationsCount()}.
	 */
	@Test
	public void testIncrementCommunicationsCount() {
		int previous = game.getNumberOfCommunications();
		game.IncrementCommunicationsCount();
		assertEquals(previous + 1, game.getNumberOfCommunications());
	}

	/**
	 * Test method for
	 * {@link edu.sjsu.cs.ghost151.Game#IncrementMovementCount()}.
	 */
	@Test
	public void testIncrementMovementCount() {
		int previous = game.getNumberOfMovements();
		game.IncrementMovementCount();
		assertEquals(previous + 1, game.getNumberOfMovements());
	}
}
