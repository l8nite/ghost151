/**
 * 
 */
package edu.sjsu.cs.ghost151;

import java.util.Random;

/**
 * @author shaung
 *
 */
public class Ghost extends BoardObject 
{
	private BoardObjectType exploredPositions[][];
	private boolean targetAcquired = false;
	
	public Ghost()
	{
		super(BoardObjectType.Ghost);
		exploredPositions = new BoardObjectType[board.getRowCount()][board.getColumnCount()];
	}

	/**
	 * @return the exploredPositions
	 */
	public BoardObjectType[][] getExploredPositions() 
	{
		return exploredPositions;
	}

	/**
	 * @param exploredPositions the exploredPositions to set
	 */
	public void setExploredPositions(BoardObjectType[][] exploredPositions) 
	{
		this.exploredPositions = exploredPositions;
	}

	public boolean IsTargetAcquired() 
	{
		return targetAcquired;
	}

	public void CommunicateWith(Ghost ghost)
	{
		BoardObjectType[][] incoming = ghost.getExploredPositions();
		
		for (int row = 0; row < incoming.length; ++row)
		{
			for (int column = 0; column < incoming[row].length; ++column)
			{
				if (exploredPositions[row][column] == null)
				{
					exploredPositions[row][column] = incoming[row][column];
				}
			}
		}
	}
	
	public void Update()
	{
		BoardObject[] surroundings = board.GetSurroundings(this);

		for (int i = 0; i < surroundings.length; ++i)
		{
			BoardObject object = surroundings[i];
			BoardPosition position = object.getPosition();

			// mark this position as explored with what we've seen there
			exploredPositions[position.getRow()][position.getColumn()] = object.getType();
			
			if (object.getType() == BoardObjectType.Target)
			{
				// move to the target space and acquire it
				targetAcquired = true;
				MoveTo(position);
			}
			
			if (object.getType() == BoardObjectType.Ghost)
			{
				// communicate with this ghost and vice-versa
				this.CommunicateWith((Ghost)object);
				((Ghost)object).CommunicateWith(this);
				Game.INSTANCE.IncrementCommunicationsCount();
			}
		}
		
		// if we haven't acquired the target during this scan, pick a random location to move to
		// TODO: move more intelligently.
		if (!targetAcquired)
		{
			Random generator = new Random();
			int randomSurrounding = generator.nextInt(surroundings.length);
			MoveTo(surroundings[randomSurrounding].getPosition());
		}
	}
	
	private void MoveTo(BoardPosition newPosition)
	{
		// we can't move into anything but empty space
		if (board.GetObjectAt(newPosition).getType() != BoardObjectType.Empty)
		{
			return;
		}
		
		BoardPosition oldPosition = this.getPosition();
		board.SetObjectAt(oldPosition, new BoardObject(BoardObjectType.Empty));	
		board.SetObjectAt(newPosition, this);
		
		Game.INSTANCE.IncrementMovementCount();
	}
}
