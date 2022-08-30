package cj.software.experiments.book.genetic.algorithms.chapter3.entity;

import java.io.Serializable;

public class Maze
		implements
		Serializable
{
	private static final long serialVersionUID = 1L;

	private final MazeField[][] maze;

	private Position startPosition = null;

	public Maze(MazeField[][] maze)
	{
		this.maze = maze;
	}

	public Position getStartPosition()
	{
		if (this.startPosition == null)
		{
			for (int rowIndex = 0; rowIndex < this.maze.length; rowIndex++)
			{
				for (int colIndex = 0; colIndex < this.maze[rowIndex].length; colIndex++)
				{
					if (MazeField.START.equals(this.maze[rowIndex][colIndex]))
					{
						this.startPosition = new Position(colIndex, rowIndex);
						break;
					}
				}
				if (this.startPosition != null)
				{
					break;
				}
			}
		}
		return this.startPosition;
	}

	public MazeField getPositionValue(Position position)
	{
		int x = position.getX();
		int y = position.getY();
		MazeField result = getPositionValue(x, y);
		return result;
	}

	public MazeField getPositionValue(int x, int y)
	{
		MazeField result;
		if (x < 0 || y < 0 || x >= this.maze.length || y >= this.maze[0].length)
		{
			result = MazeField.WALL;
		}
		else
		{
			result = this.maze[y][x];
		}
		return result;
	}

	public int getMaxX()
	{
		return this.maze[0].length;
	}

	public int getMaxY()
	{
		return this.maze.length;
	}
}
