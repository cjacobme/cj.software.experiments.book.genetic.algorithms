package cj.software.experiments.book.genetic.algorithms.chapter3.entity;

import static cj.software.experiments.book.genetic.algorithms.chapter3.entity.MazeField.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Robot
		implements
		Serializable
{
	private static final long serialVersionUID = 1L;

	private Position position;
	private Direction heading;
	int maxMoves;
	int moves;
	private int sensorValue;
	private final int sensorActions[];
	private Maze maze;
	private List<Position> route;

	public Robot(int[] sensorActions, Maze maze, int maxMoves)
	{

		this.sensorActions = this.calcSensorActions(sensorActions);
		this.maze = maze;
		Position startPosition = maze.getStartPosition();
		this.position = startPosition;
		this.sensorValue = -1;
		this.heading = Direction.EAST;
		this.maxMoves = maxMoves;
		this.moves = 0;
		this.route = new ArrayList<>();
		this.route.add(startPosition);
	}

	// TODO in service class auslagern
	private int[] calcSensorActions(int[] source)
	{
		int numActions = source.length / 2;
		int[] result = new int[numActions];
		for (int sensorValue = 0; sensorValue < numActions; sensorValue++)
		{
			int sensorAction = 0;
			if (source[sensorValue * 2] == 1)
			{
				sensorAction += 2;
			}
			if (source[(sensorValue * 2) + 1] == 1)
			{
				sensorAction += 1;
			}
			result[sensorValue] = sensorAction;
		}
		return result;
	}

	public int getNextAction()
	{
		return this.sensorActions[this.getSensorValue()];
	}

	// TODO in service auslagern
	public int getSensorValue()
	{
		if (this.sensorValue > -1)
		{
			return this.sensorValue;
		}
		boolean frontSensor, frontLeftSensor, frontRightSensor, leftSensor, rightSensor, backSensor;
		switch (this.heading)
		{
		case NORTH:
			frontSensor = WALL.equals(
					this.maze.getPositionValue(this.position.getX(), this.position.getY() - 1));
			frontLeftSensor = WALL.equals(
					this.maze.getPositionValue(this.position.getX() - 1, this.position.getY() - 1));
			frontRightSensor = WALL.equals(
					this.maze.getPositionValue(this.position.getX() + 1, this.position.getY() - 1));
			leftSensor = WALL.equals(
					this.maze.getPositionValue(this.position.getX() - 1, this.position.getY()));
			rightSensor = WALL.equals(
					this.maze.getPositionValue(this.position.getX() + 1, this.position.getY()));
			backSensor = WALL.equals(
					this.maze.getPositionValue(this.position.getX(), this.position.getY() + 1));
			break;
		case EAST:
			frontSensor = WALL.equals(
					this.maze.getPositionValue(this.position.getX() + 1, this.position.getY()));
			frontLeftSensor = WALL.equals(
					this.maze.getPositionValue(this.position.getX() + 1, this.position.getY() - 1));
			frontRightSensor = WALL.equals(
					this.maze.getPositionValue(this.position.getX() + 1, this.position.getY() + 1));
			break;
		default:
			throw new UnsupportedOperationException("unknown: " + this.heading);
		}

		int result = 0;
		if (frontSensor)
		{
			result += 1;
		}
		if (frontLeftSensor)
		{
			result += 2;
		}
		if (frontRightSensor)
		{
			result += 4;
		}
		if (leftSensor)
		{
			result += 8;
		}
		if (rightSensor)
		{
			result += 16;
		}
		if (backSensor)
		{
			result += 32;
		}
		this.sensorValue = result;
		return result;
	}
}
