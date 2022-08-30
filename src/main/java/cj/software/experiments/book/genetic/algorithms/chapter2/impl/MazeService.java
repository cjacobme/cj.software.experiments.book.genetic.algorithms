package cj.software.experiments.book.genetic.algorithms.chapter2.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cj.software.experiments.book.genetic.algorithms.chapter2.entity.Maze;
import cj.software.experiments.book.genetic.algorithms.chapter2.entity.MazeField;
import cj.software.experiments.book.genetic.algorithms.chapter2.entity.Position;

public class MazeService
{
	public int scoreRoute(Maze maze, List<Position> route)
	{
		int result = 0;

		Set<Position> visiteds = new HashSet<>();

		for (Position position : route)
		{
			if (MazeField.ROUTE.equals(maze.getPositionValue(position))
					&& !visiteds.contains(position))
			{
				result++;
				visiteds.add(position);
			}
		}

		return result;
	}
}
