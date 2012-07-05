/**
 * 
 */
package edu.sjsu.cs.ghost151;

import java.util.Random;

/**
 * @author shaung
 *
 */
public enum Game 
{
	INSTANCE; // singleton
	
	private int numberOfCommunications;
	private int numberOfGhosts;
	private int numberOfGameLoops;
	private int numberOfMovements;
	private Ghost ghosts[];
	private Board board;
	private boolean isRunning;
	
	public void Run(int numberOfGhosts)
	{
		this.numberOfGhosts = numberOfGhosts;

		ConfigureBoard();

		isRunning = true;

		while (isRunning)
		{
			Update();
			Render();
			++numberOfGameLoops;
			
			// slow down the game loop so that rendering is human-speed
			try {
				Thread.sleep(500); 
			} 
			catch (InterruptedException interruptedException) { }
		}
	
		PrintStatistics();
	}
	
	public void PrintStatistics()
	{
		System.out.println("Number of game loops: " + numberOfGameLoops);
		System.out.println("Number of movements: " + numberOfMovements);
		System.out.println("Number of communications: " + numberOfCommunications);
	}
	
	public void ConfigureBoard()
	{
		board = Board.INSTANCE;
		ConfigureGhosts();
		ConfigureTarget();
	}
	
	private void ConfigureGhosts()
	{
		Random generator = new Random();

		ghosts = new Ghost[numberOfGhosts];
		
		for (int i = 0; i < numberOfGhosts; ++i)
		{
			ghosts[i] = new Ghost();

			// we always know that position 0,0 won't be empty (it's a wall)
			BoardPosition position = new BoardPosition(0, 0);
			
			while (board.GetObjectAt(position).getType() != BoardObjectType.Empty)
			{
				// pick a random position on the board for this ghost, avoid the walls
				position.setRow(generator.nextInt(board.getRowCount() - 2) + 1);
				position.setColumn(generator.nextInt(board.getColumnCount() - 2) + 1);
			}

			board.SetObjectAt(position, ghosts[i]);
		}
	}
	
	private void ConfigureTarget()
	{
		Random generator = new Random();
		
		// we always know that position 0,0 won't be empty (it's a wall)
		BoardPosition position = new BoardPosition(0, 0);
		
		while (board.GetObjectAt(position).getType() != BoardObjectType.Empty)
		{
			// pick a random position on the board for this ghost, avoid the walls
			position.setRow(generator.nextInt(board.getRowCount() - 2) + 1);
			position.setColumn(generator.nextInt(board.getColumnCount() - 2) + 1);
		}

		BoardObject target = new BoardObject(BoardObjectType.Target);
		
		board.SetObjectAt(position, target);
	}
	
	public void Update()
	{
		for (int i = 0; i < numberOfGhosts; ++i)
		{
			Ghost ghost = ghosts[i];
			ghost.Update();

			if (ghost.IsTargetAcquired())
			{
				isRunning = false;
				return;
			}
		}
	}
	
	public void Render()
	{
		System.out.print(board.toString());
	}
	
	public void IncrementCommunicationsCount()
	{
		++numberOfCommunications;
	}
	
	public void IncrementMovementCount()
	{
		++numberOfMovements;
	}
	
	/**
	 * @return the numberOfCommunications
	 */
	public int getNumberOfCommunications() 
	{
		return numberOfCommunications;
	}
	
	/**
	 * @param numberOfCommunications the numberOfCommunications to set
	 */
	public void setNumberOfCommunications(int numberOfCommunications) 
	{
		this.numberOfCommunications = numberOfCommunications;
	}
	
	/**
	 * @return the numberOfGhosts
	 */
	public int getNumberOfGhosts() 
	{
		return numberOfGhosts;
	}
	
	/**
	 * @param numberOfGhosts the numberOfGhosts to set
	 */
	public void setNumberOfGhosts(int numberOfGhosts) 
	{
		this.numberOfGhosts = numberOfGhosts;
	}
	
	/**
	 * @return the numberOfMovements
	 */
	public int getNumberOfMovements() 
	{
		return numberOfMovements;
	}
	
	/**
	 * @param numberOfMovements the numberOfMovements to set
	 */
	public void setNumberOfMovements(int numberOfMovements)
	{
		this.numberOfMovements = numberOfMovements;
	}
	
	/**
	 * @return the ghosts
	 */
	public Ghost[] getGhosts() 
	{
		return ghosts;
	}
	
	/**
	 * @param ghosts the ghosts to set
	 */
	public void setGhosts(Ghost[] ghosts) 
	{
		this.ghosts = ghosts;
	}
	
	/**
	 * @return the board
	 */
	public Board getBoard() 
	{
		return board;
	}
	
	/**
	 * @param board the board to set
	 */
	public void setBoard(Board board) 
	{
		this.board = board;
	}
}
