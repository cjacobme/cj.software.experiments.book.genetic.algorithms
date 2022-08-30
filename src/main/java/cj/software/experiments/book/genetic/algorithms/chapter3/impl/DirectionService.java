package cj.software.experiments.book.genetic.algorithms.chapter3.impl;

import static cj.software.experiments.book.genetic.algorithms.chapter3.entity.Direction.*;

import java.util.HashMap;
import java.util.Map;

import cj.software.experiments.book.genetic.algorithms.chapter3.entity.Direction;

public class DirectionService
{
	private static final Map<Direction, Direction> rightTurns = createRightTurns();

	private static final Map<Direction, Direction> leftTurns = createLeftTurns();

	private static Map<Direction, Direction> createRightTurns()
	{
		Map<Direction, Direction> result = new HashMap<>();
		result.put(NORTH, EAST);
		result.put(EAST, SOUTH);
		result.put(SOUTH, WEST);
		result.put(WEST, NORTH);
		return result;
	}

	private static Map<Direction, Direction> createLeftTurns()
	{
		Map<Direction, Direction> result = new HashMap<>();
		result.put(NORTH, WEST);
		result.put(WEST, SOUTH);
		result.put(SOUTH, EAST);
		result.put(EAST, NORTH);
		return result;
	}
}
