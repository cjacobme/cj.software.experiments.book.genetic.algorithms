package cj.software.experiments.book.genetic.algorithms.chapter3.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cj.software.experiments.book.genetic.algorithms.chapter3.impl.DirectionService;

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

	private DirectionService directionService = new DirectionService();

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

	public void run()
	{
		while (true)
		{
			this.moves++;
			if (this.getNextAction() == 0)
			{
				break;
			}
			if (MazeField.GOAL.equals(this.maze.getPositionValue(this.position)))
			{
				break;
			}
			if (this.moves > this.maxMoves)
			{
				break;
			}
			this.executeNextAction();
		}
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
		int sensorValue = this.getSensorValue();
		int result = this.sensorActions[sensorValue];
		return result;
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
			frontSensor = this.maze.isWall(this.position.getX(), this.position.getY() - 1);
			frontLeftSensor = this.maze.isWall(this.position.getX() - 1, this.position.getY() - 1);
			frontRightSensor = this.maze.isWall(this.position.getX() + 1, this.position.getY() - 1);
			leftSensor = this.maze.isWall(this.position.getX() - 1, this.position.getY());
			rightSensor = this.maze.isWall(this.position.getX() + 1, this.position.getY());
			backSensor = this.maze.isWall(this.position.getX(), this.position.getY() + 1);
			break;
		case EAST:
			frontSensor = this.maze.isWall(this.position.getX() + 1, this.position.getY());
			frontLeftSensor = this.maze.isWall(this.position.getX() + 1, this.position.getY() - 1);
			frontRightSensor = this.maze.isWall(this.position.getX() + 1, this.position.getY() + 1);
			leftSensor = this.maze.isWall(this.position.getX(), this.position.getY() - 1);
			rightSensor = this.maze.isWall(this.position.getX(), this.position.getY() + 1);
			backSensor = this.maze.isWall(this.position.getX() - 1, this.position.getY());
			break;
		case SOUTH:
			frontSensor = this.maze.isWall(this.position.getX(), this.position.getY() + 1);
			frontLeftSensor = this.maze.isWall(this.position.getX() + 1, this.position.getY() + 1);
			frontRightSensor = this.maze.isWall(this.position.getX() - 1, this.position.getY() + 1);
			leftSensor = this.maze.isWall(this.position.getX() + 1, this.position.getY());
			rightSensor = this.maze.isWall(this.position.getX() - 1, this.position.getY());
			backSensor = this.maze.isWall(this.position.getX(), this.position.getY() - 1);
			break;
		case WEST:
			frontSensor = this.maze.isWall(this.position.getX() - 1, this.position.getY());
			frontLeftSensor = this.maze.isWall(this.position.getX() - 1, this.position.getY() + 1);
			frontRightSensor = this.maze.isWall(this.position.getX() - 1, this.position.getY() - 1);
			leftSensor = this.maze.isWall(this.position.getX(), this.position.getY() + 1);
			rightSensor = this.maze.isWall(this.position.getX(), this.position.getY() - 1);
			backSensor = this.maze.isWall(this.position.getX() + 1, this.position.getY());
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

	public void executeNextAction()
	{
		int nextAction = getNextAction();
		switch (nextAction)
		{
		case 1:
			moveForward();
			break;
		case 2:
			this.heading = this.directionService.right(this.heading);
			break;
		case 3:
			this.heading = this.directionService.left(this.heading);
			break;
		}
		this.sensorValue = -1;
	}

	private void moveForward()
	{
		int currentX = this.position.getX();
		int currentY = this.position.getY();
		switch (this.heading)
		{
		case NORTH:
			this.position.decrY();
			break;
		case EAST:
			this.position.incrX(this.maze.getMaxX());
			break;
		case SOUTH:
			this.position.incrY(this.maze.getMaxY());
			break;
		case WEST:
			this.position.decrX();
			break;
		}

		// We can't move here
		if (this.maze.isWall(this.position.getX(), this.position.getY()))
		{
			this.position = new Position(currentX, currentY);
		}
		else if (currentX != this.position.getX() || currentY != this.position.getY())
		{
			this.route.add(this.position);
		}
	}

	public String describeRoute()
	{
		String line = String.format("%n");
		StringBuilder sb = new StringBuilder();
		for (Position position : this.route)
		{
			sb.append(position).append(line);
		}
		return sb.toString();
	}

	public List<Position> getRoute()
	{
		return this.route;
	}
}
