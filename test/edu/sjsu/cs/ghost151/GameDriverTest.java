package edu.sjsu.cs.ghost151;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameDriverTest {

	@Test
	public void test() {
		GameDriver g = new GameDriver();
		assertNotNull(g);

		String[] args = {"GameDriverTest", "-5"};
		GameDriver.main(args);
		assertEquals(0, Game.INSTANCE.getNumberOfGameLoops());
		
		args[1] = "200";
		GameDriver.main(args);
		assertEquals(0, Game.INSTANCE.getNumberOfGameLoops());
	
		args[1] = null;
		Game.INSTANCE.getBoard().setRenderer(new InvisibleBoardRenderer());
		GameDriver.main(args);
		assertTrue(Game.INSTANCE.getNumberOfGameLoops() > 0);
	}

}
