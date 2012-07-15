package edu.sjsu.cs.ghost151;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConsoleGameDriverTest {

	@Test
	public void test() {
		ConsoleGameDriver g = new ConsoleGameDriver();
		assertNotNull(g);

		String[] args = {"ConsoleGameDriverTest", "-5"};
		ConsoleGameDriver.main(args);
		assertEquals(0, Game.INSTANCE.getNumberOfGameLoops());
		
		args[1] = "200";
		ConsoleGameDriver.main(args);
		assertEquals(0, Game.INSTANCE.getNumberOfGameLoops());
	
		args[1] = null;
		Game.INSTANCE.getBoard().setRenderer(new InvisibleBoardRenderer());
		ConsoleGameDriver.main(args);
		assertTrue(Game.INSTANCE.getNumberOfGameLoops() > 0);
	}

}
