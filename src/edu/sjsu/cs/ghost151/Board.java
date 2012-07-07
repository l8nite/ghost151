package edu.sjsu.cs.ghost151;

import java.util.ArrayList;

/**
 * <b>Board</b>
 * provides the Grid for the simulation. Additionally, it will:
 * <ul>
 * <li>set the walls on the perimeter of the Board
 * <ii>place an object at a given position by calling BoardObject
 * <li>get the surroundings of a given position by calling BoardObject
 * </ul>
 *
 * @author      Alben Cheung
 * @author      MD Ashfaqul Islam
 * @author      Shaun Guth
 * @author      Jerry Phul
 */
public enum Board {
	INSTANCE; // singleton

	private static final int ROWS = 10;
	private static final int COLUMNS = 20;

	private BoardObject grid[][];
	

        /**
         * Constructs the default Board object.
         */
	private Board()
	{
		grid = new BoardObject[ROWS][COLUMNS];
		
		for (int row = 0; row < ROWS; ++row)
		{
			for (int column = 0; column < COLUMNS; ++column)
			{
				BoardObject object = new BoardObject(BoardObjectType.Empty);
				
				// set all non-edge to empty, all edges to walls
				if (row == 0 || row == ROWS - 1 || column == 0 || column == COLUMNS - 1)
				{
					object.setType(BoardObjectType.Wall);
				}
				
				SetObjectAt(new BoardPosition(row, column), object);
			}
		}
	}
	

        /**
         * Convert to string.
         */
	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		for (int row = 0; row < ROWS; ++row)
		{
			for (int column = 0; column < COLUMNS; ++column)
			{
				BoardObject object = grid[row][column];
				sb.append(object.toString());
			}
			
			sb.append("\n");
		}

		return sb.toString();
	}
	
	
        /**
         * Takes any object and assigns it to a position on the Board.
         *
	 * @param boardPosition     the position to set the object at
	 * @param boardObject       the object to place
         */
	public void SetObjectAt(BoardPosition boardPosition, BoardObject boardObject)
	{
		boardObject.setPosition(boardPosition);
		grid[boardPosition.getRow()][boardPosition.getColumn()] = boardObject;
	}
	

        /**
         * get the object at a given Board position.
         *
         * @param boardPosition       the board position to get object from
         */
	public BoardObject GetObjectAt(BoardPosition boardPosition)
	{
		return grid[boardPosition.getRow()][boardPosition.getColumn()];
	}
	

        /**
         * Retrieve the surrounding information for a given object on the Board.
         *
         * @param boardObject       The object to get surroundings of
         */
	public BoardObject[] GetSurroundings(BoardObject boardObject)
	{
		ArrayList<BoardObject> surroundings = new ArrayList<BoardObject>();
		
		BoardPosition center = boardObject.getPosition();
		
		int row = center.getRow();
		int column = center.getColumn();

		// above left
		if ((row - 1) >= 0 && (column - 1) >= 0)
		{
			surroundings.add(grid[row - 1][column - 1]);
		}
		
		// above center
		if ((row - 1) >= 0)
		{
			surroundings.add(grid[row - 1][column]);
		}
		
		// above right
		if ((row - 1) >= 0 && (column + 1) < COLUMNS)
		{
			surroundings.add(grid[row - 1][column + 1]);
		}

		// left
		if ((column - 1) >= 0)
		{
			surroundings.add(grid[row][column - 1]);
		}

		// right
		if ((column + 1) < COLUMNS)
		{
			surroundings.add(grid[row][column + 1]);
		}

		// below left
		if ((row + 1) < ROWS && (column - 1) >= 0)
		{
			surroundings.add(grid[row + 1][column - 1]);
		}

		// below center
		if ((row + 1) < ROWS)
		{
			surroundings.add(grid[row + 1][column]);
		}
		
		// below right
		if ((row + 1) < ROWS && (column + 1) < COLUMNS)
		{
			surroundings.add(grid[row + 1][column + 1]);
		}
		
		BoardObject[] surroundingsArray = new BoardObject[surroundings.size()];
		surroundings.toArray(surroundingsArray);
		return surroundingsArray;
	}


        /**
         * Get the grid object.
         *
         * @return          the grid
         */
	public BoardObject[][] getGrid()
	{
		return grid;
	}


        /**
         * Set the grid object.
         *
         * @param grid      the grid to set
         */
	public void setGrid(BoardObject[][] grid) 
	{
		this.grid = grid;
	}


        /**
         * Get the row count of the Board object.
         *
         * @return          the row count.
         */
	public int getRowCount()
	{
		return ROWS;
	}
	

        /**
         * Return the column count for the Board object.
         *
         * @return          the column count.
         */
	public int getColumnCount() 
	{
		return COLUMNS;
	}
	
}
